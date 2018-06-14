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

    public List<TaskCounter> queryList(){
       return null;
    }


}
