package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.repository.TaskCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<TaskCounter> queryList() {
        List<TaskCounter> counters = taskCounterRepository.findAll();
        return counters;
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
