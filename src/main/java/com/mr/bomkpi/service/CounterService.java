package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CounterService {

    @Autowired
    private TaskCounterRepository taskCounterRepository;

    public TaskCounter readById(String id){
       return  taskCounterRepository.getOne(Long.valueOf(id));
    }


    /**
     *  根据所属仓库，查询所有的仓库柜台。
     *  如果没有所属仓库，查看所有的柜台。柜台只有库管以上领导能查看修改。
     * @param userWhses
     * @return
     */
    public List<TaskCounter> queryList(List<UserWhseVo> userWhses) {

        List<TaskCounter> counterRecoders = new ArrayList<>();
//这个循环不如用SQL,in合适呢
        if(userWhses != null){
            for (UserWhseVo userWhse : userWhses) {
                List<TaskCounter> list = taskCounterRepository.findAllByWhseCode(userWhse.getWhseCode());
                if(list.size() > 0){
                    counterRecoders.addAll(list);
                }
            }
        }else{

             counterRecoders = taskCounterRepository.findAll();
        }
        return counterRecoders;
    }
    public List<TaskCounter> findAllByWhseCode(String whseCode){
        List<TaskCounter> counters = taskCounterRepository.findAllByWhseCode(whseCode);
        return counters;
    }

    public void save(TaskCounter counter) {
        taskCounterRepository.save(counter);
    }

    public boolean isExsist(String countercode, String whseCode) {
        return taskCounterRepository.existsByCounterCodeAndWhseCode(countercode, whseCode);
    }

    public TaskCounter getIdByCounterCodeAndWhseCode(String countercode, String whseCode){
       return  taskCounterRepository.readByCounterCodeAndWhseCode(countercode,whseCode);
    }

    /**
     * 根据条件查找，搜索，包含模糊搜索
     * 主要是拼接字符串SQL来进行查找
     * @param userWhses
     * @return
     */
    public List<TaskCounter> queryListOnCondition(TaskCounter counter, List<UserWhseVo> userWhses, String fuzzy, String fuzzySearch){
        List<TaskCounter> counterRecoders = new ArrayList<>();

        //获取用户过滤框里的字符
        List<String> sArray = new ArrayList<String>();
        if ("true".equals(fuzzySearch)) {
            //模糊查询
            if (fuzzy != null && !"".equals(fuzzy)) {
                sArray.add(" counterCode like '%" + fuzzy + "%'");
                sArray.add(" whseCode like '%" + fuzzy + "%'");
                sArray.add(" creater like '%" + fuzzy + "%'");
                sArray.add(" counterStatus like '%" + fuzzy + "%'");
            }
        }else {
            if (counter.getCounterCode() != null && !"".equals(counter.getCounterCode())) {
                sArray.add(" counterCode like '%" + counter.getCounterCode() + "%'");
            }
            if (counter.getWhseCode() != null && !"".equals(counter.getWhseCode())) {
                sArray.add(" whseCode like '%" + counter.getWhseCode() + "%'");
            }
            if (counter.getCreater() != null && !"".equals(counter.getCreater())) {
                sArray.add(" creater like '%" + counter.getCreater() + "%'");
            }
            if (counter.getCounterStatus() != null && !"".equals(counter.getCounterStatus())) {
                sArray.add(" counterStatus like '%" + counter.getCounterStatus() + "%'");
            }

        }
        String condition = "";
        String whseCodeStr ="";
        if(userWhses.size() == 1){
            whseCodeStr=" whseCode= '"+userWhses.get(0).getWhseCode()+"' ";
        }else if (userWhses.size() > 1) {
            for (int j = 0; j < userWhses.size() - 1; j++) {
                whseCodeStr += " whseCode= '"+ userWhses.get(j).getWhseCode() + "' or ";
            }
            whseCodeStr +=" whseCode= '"+  userWhses.get(userWhses.size() - 1).getWhseCode()+"'";
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
//人员没有所属仓库，查看全部，柜台
        if(StringUtil.isEmptyOrNull(whseCodeStr)){
            counterRecoders = taskCounterRepository.findAll();
        }else{
            if(StringUtil.isEmptyOrNull(individualSearch)){
                condition=whseCodeStr;
            }else{
                condition=whseCodeStr+" or "+individualSearch;
            }
            counterRecoders = taskCounterRepository.findAllByConditon(condition);
        }



        return counterRecoders;
    }



}
