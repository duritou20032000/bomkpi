package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.TaskCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CounterService {

    @PersistenceContext
    private EntityManager entityManager;

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
     *
     * 这个这个主要使用JPA2.0 的criteriaBuilder 的方法来进行，标准的查找方式很好。
     *
     */
    public List<TaskCounter> queryListOnCondition(TaskCounter counter, List<UserWhseVo> userWhses, String fuzzy, String fuzzySearch) {

        List<TaskCounter> counters = taskCounterRepository.findAll(new Specification<TaskCounter>() {
            @Override
            public Predicate toPredicate(Root<TaskCounter> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(userWhses.size() > 0 && userWhses != null) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("whseCode"));
                    for (UserWhseVo userWhs : userWhses) {
                        in.value(userWhs.getWhseCode());
                    }
                    list.add(in);
                }

                if("true".equals(fuzzySearch)){
                    if(fuzzy != null && !"".equals(fuzzy)){
                        Predicate p1 = criteriaBuilder.like(root.get("counterCode").as(String.class), "%" + fuzzy + "%");
                        Predicate p2 = criteriaBuilder.or(criteriaBuilder.like(root.get("whseCode").as(String.class), "%" + fuzzy + "%"),p1);
                        Predicate p3 = criteriaBuilder.or(criteriaBuilder.like(root.get("creater").as(String.class), "%" + fuzzy + "%"),p2);
                        list.add(p3);
                    }
                }else{
                    if (counter.getCounterCode() != null && !"".equals(counter.getCounterCode())) {
                        Predicate p4 = criteriaBuilder.like(root.get("counterCode").as(String.class), "%" + counter.getCounterCode() + "%");
                        list.add(p4);
                    }
                    if (counter.getWhseCode() != null && !"".equals(counter.getWhseCode())) {
                        Predicate p5 = criteriaBuilder.like(root.get("whseCode").as(String.class), "%" + counter.getWhseCode() + "%");
                        list.add(p5);
                    }
                    if (counter.getCreater() != null && !"".equals(counter.getCreater())) {
                        Predicate p6 = criteriaBuilder.like(root.get("creater").as(String.class), "%" + counter.getCreater() + "%");
                        list.add(p6);
                    }

                }
                Predicate[] p = list.toArray(new Predicate[0]);
                return criteriaBuilder.and(p);
            }
        });
        return counters;
    }

}
