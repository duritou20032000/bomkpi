package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskOrderRepository;
import com.mr.bomkpi.repository.TaskRepository;
import com.mr.bomkpi.util.DateUtil;
import com.mr.bomkpi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    public static Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskOrderRepository orderRepository;
    @Autowired
    private TaskCounterRepository counterRepository;


    /**
     * 根据条件查找，搜索，包含模糊搜索
     * 主要是拼接字符串SQL来进行查找
     *
     * 这个这个主要使用JPA2.0 的criteriaBuilder 的方法来进行，标准的查找方式很好。
     *
     */
    public List<Task> queryListOnCondition(Task task, List<UserWhseVo> userWhses, String fuzzy, String fuzzySearch) {

        List<Task> tasks = taskRepository.findAll(new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

//                if(userWhses.size() > 0 && userWhses != null) {
//                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("whseCode"));
//                    for (UserWhseVo userWhs : userWhses) {
//                        in.value(userWhs.getWhseCode());
//                    }
//                    list.add(in);
//                }

                if("true".equals(fuzzySearch)){
                    if(fuzzy != null && !"".equals(fuzzy)){
                        Predicate p1 = criteriaBuilder.like(root.get("taskCode").as(String.class), "%" + fuzzy + "%");
                        Predicate p2 = criteriaBuilder.or(criteriaBuilder.like(root.get("orderCode").as(String.class), "%" + fuzzy + "%"),p1);
                        Predicate p3 = criteriaBuilder.or(criteriaBuilder.like(root.get("productCode").as(String.class), "%" + fuzzy + "%"),p2);
                        Predicate p4 = criteriaBuilder.or(criteriaBuilder.like(root.get("counterCode").as(String.class), "%" + fuzzy + "%"),p3);
                        list.add(p4);
                    }
                }else{
                    if (task.getTaskCode() != null && !"".equals(task.getTaskCode())) {
                        Predicate p4 = criteriaBuilder.like(root.get("taskCode").as(String.class), "%" + task.getTaskCode() + "%");
                        list.add(p4);
                    }
                    if (task.getOrderCode() != null && !"".equals(task.getOrderCode())) {
                        Predicate p5 = criteriaBuilder.like(root.get("orderCode").as(String.class), "%" + task.getOrderCode() + "%");
                        list.add(p5);
                    }
                    if (task.getCounterCode() != null && !"".equals(task.getCounterCode())) {
                        Predicate p6 = criteriaBuilder.like(root.get("counterCode").as(String.class), "%" + task.getCounterCode() + "%");
                        list.add(p6);
                    }
                    if (task.getProductCode() != null && !"".equals(task.getProductCode())) {
                        Predicate p7 = criteriaBuilder.like(root.get("productCode").as(String.class), "%" + task.getProductCode() + "%");
                        list.add(p7);
                    }

                }
                Predicate[] p = list.toArray(new Predicate[0]);
                return criteriaBuilder.and(p);
            }
        });
        return tasks;
    }



}
