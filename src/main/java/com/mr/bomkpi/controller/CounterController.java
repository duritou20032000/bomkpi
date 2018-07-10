package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.UserRepository;
import com.mr.bomkpi.repository.UserWhseVoRepository;
import com.mr.bomkpi.repository.WhseRepository;
import com.mr.bomkpi.service.CounterService;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

/**
 * @author Administrator
 */
@RestController
public class CounterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWhseVoRepository userWhseVoRepository;
    @Autowired
    private CounterService counterService;
    @Autowired
    private WhseRepository whseRepository;

    /**
     * 当前用户的所属仓库
     *
     * @return
     */
    @PostMapping("/counter/getCurUserWhseCode")
    public List<UserWhseVo> getCurUserWhseCode(String username) {
        List<UserWhseVo> list = userWhseVoRepository.findAllByUserName(username,new Sort(Sort.Direction.ASC,"whseCode"));
        return list;
    }

    /**
     * 1.根据用户所属的仓库显示所属的仓库的柜台
     * 2.没有所属仓库，默认所有柜台，或所属多个仓库，显示多个柜台。
     *
     * @return
     */
    @PostMapping("/counter/getCounters")
    public Map<String,List<TaskCounter>> getCounter(TaskCounter counter,Principal principal,String fuzzy,String fuzzySearch) {
        String username=principal.getName();
        Map<String,List<TaskCounter>> map = new HashMap<>();
        List<TaskCounter> counters = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();
        //获取用户过滤框里的字符
        List<String> sArray = new ArrayList<String>();

        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
             userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }
        if ("true".equals(fuzzySearch)) {
            //模糊查询
            if (fuzzy!=null&&!"".equals(fuzzy)) {
                sArray.add(" counterCode like '%" + fuzzy + "%'");
                sArray.add(" whseCode like '%" + fuzzy + "%'");
                sArray.add(" creater like '%" + fuzzy + "%'");
                sArray.add(" counterStatus like '%" + fuzzy + "%'");
            }else{
                if (counter.getCounterCode()!=null&&!"".equals(counter.getCounterCode())) {
                    sArray.add(" counterCode like '%" + counter.getCounterCode() + "%'");
                }
                if (counter.getWhseCode()!=null&&!"".equals(counter.getWhseCode())) {
                    sArray.add(" whseCode like '%" + counter.getWhseCode() + "%'");
                }
                if (counter.getCreater()!=null&&!"".equals(counter.getCreater())) {
                    sArray.add(" creater like '%" + counter.getCreater() + "%'");
                }
                if (counter.getCounterStatus()!=null&&!"".equals(counter.getCounterStatus())) {
                    sArray.add(" counterStatus like '%" + counter.getCounterStatus() + "%'");
                }

            }
            String individualSearch = "";
            if (sArray.size() == 1) {
                individualSearch = sArray.get(0);
            } else if (sArray.size() > 1) {
                for (int i = 0; i < sArray.size() - 1; i++) {
                    individualSearch += sArray.get(i) + " or ";
                }
                individualSearch += sArray.get(sArray.size() - 1);
            }
        }

        counters = counterService.queryList(userWhses);
        map.put("data",counters);
        return map;
    }

    /**
     * 根据仓库搜索
     */
    @PostMapping("/counter/search")
    public Map<String,List<TaskCounter>> search(String whseCode) {
        Map<String,List<TaskCounter>> map = new HashMap<>();
        List<TaskCounter> counters = counterService.findAllByWhseCode(whseCode);
        map.put("data",counters);
        return map;
    }


    /**
     * 1.根据用户所属的仓库显示仓库名称
     * 2.多个或没有，默认显示第一个。
     *
     * @param model
     * @return
     */
    @GetMapping("/counter/form")
    public String addCounter(Model model, String id, Principal principal) {
        String uname = principal.getName();
        if (!StringUtils.isEmpty(uname)) {
            //得到用户仓库，表中没有，这个功能暂时放下
        }
        if (!StringUtils.isEmpty(id)) {
            TaskCounter counter = counterService.readById(id);
            model.addAttribute("counter", counter);
        }
        return "/counter/form";
    }

    /**
     * 保存仓库的柜台信息
     * 1.一个仓库和柜台不能重复
     *
     * @param counter
     * @return
     */
    @PostMapping("/counter/save")
    public String save(TaskCounter counter, Principal principal) {

        String code = counter.getCounterCode();
        String whsecode = counter.getWhseCode();
        if (counterService.isExsist(code, whsecode)) {
            TaskCounter tc = counterService.getIdByCounterCodeAndWhseCode(code, whsecode);
            counter.setId(tc.getId());
            counter.setCreater(tc.getCreater());
            counter.setCreationDate(new Date());
            counter.setLastModifyName(principal.getName());
            counter.setLastModifyDate(new Date());
        } else {
            counter.setCreater(principal.getName());
            counter.setCreationDate(new Date());
        }
        counterService.save(counter);
        return "redirect:/counter/getCounters";
    }

    @PostMapping("/counter/isExist")
    public Boolean isExist(TaskCounter counter) {
        String code = counter.getCounterCode();
        String whsecode = counter.getWhseCode();
        counterService.isExsist(code,whsecode);
        return counterService.isExsist(code,whsecode);
    }




}
