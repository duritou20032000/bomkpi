package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.entity.Whse;
import com.mr.bomkpi.repository.UserRepository;
import com.mr.bomkpi.repository.UserWhseVoRepository;
import com.mr.bomkpi.repository.WhseRepository;
import com.mr.bomkpi.service.CounterService;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * 1.根据用户所属的仓库显示所属的仓库的柜台
     * 2.没有所属仓库，默认所有柜台，或所属多个仓库，显示多个柜台。
     *
     * @return
     */
    @GetMapping("/counter/getCounters")
    public List<TaskCounter> getCounter(String username) {

        List<TaskCounter> counters = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();

        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
             userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }
         counters = counterService.queryList(userWhses);
        return counters;
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

    /**
     * 联动仓库编码和名称
     *
     * @return
     */
    @PostMapping("/counter/getCurUserWhseCode")
    public List<UserWhseVo> getCurUserWhseCode(String username) {
        List<UserWhseVo> list = userWhseVoRepository.findAllByUserName(username,new Sort(Sort.Direction.ASC,"whseCode"));
        return list;
    }

    /**
     * 联动仓库编码和名称
     *
     * @return
     */
    @PostMapping("/counter/getWhseNameByCode/{val}")
    @ResponseBody
    public String getWhseNameByCode(@PathVariable("val") String code) {

        Whse whse = whseRepository.findByWhseCode(code);
        return whse.getWhseName();
    }

    /**
     * 根据仓库搜索
     */
    @PostMapping("/counter/search")
    public String search(Model model, String whseCode) {
        if (StringUtil.isEmptyOrNull(whseCode)) {
            return "redirect:/counter/getCounters";
        }
        List<TaskCounter> counters = counterService.findAllByWhseCode(whseCode);
        model.addAttribute("counters", counters);
        model.addAttribute("whseCode", whseCode);
        return "/counter/list";
    }

    @GetMapping("/counter/clear")
    public String clear(Model model) {
        return "redirect:/counter/getCounters";
    }

}
