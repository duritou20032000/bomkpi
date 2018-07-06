package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.TaskCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
