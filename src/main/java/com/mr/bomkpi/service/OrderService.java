package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskOrderRepository;
import com.mr.bomkpi.repository.TaskRepository;
import com.mr.bomkpi.util.DateUtil;
import com.mr.bomkpi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    public static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskOrderRepository orderRepository;
    @Autowired
    private TaskCounterRepository counterRepository;

    /**
     * 一般都是用1次分配完成任务
     *  1.初次分配，根据分配了数量的柜台生成任务
     *  2.分配数量0的柜台，不生成任务
     */

    public void saveTasks(TaskOrder order, Principal principal){

        //通过仓库获得柜台，第一轮柜台分配
        String whseCode =  order.getWhseCode();
        if(!StringUtil.isEmptyOrNull(whseCode)){
            List<TaskCounter> counters = counterRepository.findAllByWhseCode(whseCode);
            if(counters != null){

                String newPlan = order.getLastAllocationPlan();
                String[] plans = newPlan.split(",");
                //后台验证分配完
                if(isAllocationOver(plans, Double.valueOf(order.getProductCount()))){
                    persistTasks(order,plans,counters,principal);
                }
                persistOrderStatus(order);
            }
        }
    }
    // 判断是否已经将订单数量分配完
    protected boolean isAllocationOver(String[] str,Double target){
        Double d = 0.0d;
        Double t = 0.0d;
         for (String s : str) {
            t = Double.valueOf((s));
            d=t+d;
         }
        return target.equals(d);
     }
//     保存任务到数据库
    protected  void persistTasks(TaskOrder order,String[] plans,List<TaskCounter> counters,Principal principal){
        for (int i = 0; i < plans.length; i++) {

            String taskcode = order.getOrderCode()+"-"+ DateUtil.getCurrStrDate("yyyyMMddHHMMSS")+"-"+i;
            String  code = counters.get(i).getCounterCode();
            String  name = counters.get(i).getCounterName();
            String  productAmount =    plans[i];
            if(!Double.valueOf(0).equals(Double.valueOf(productAmount))){

                Task task = new Task();
                if(task.getCreaterName() == null){
                    task.setCreaterName(principal.getName());
                    task.setCreationDate(new Date());
                }else{
                    task.setLastModifyUserName(principal.getName());
                    task.setLastModifyDate(new Date());
                }

                task.setTaskCode(taskcode);
                task.setCounterCode(code);
                task.setCounterName(name);
                task.setOrderCode(order.getOrderCode());
                task.setProductCode(order.getProductCode());
                task.setProductCount(productAmount);
                task.setProductUnit(order.getProductUnit());
                task.setTaskStatus(1);
                taskRepository.save(task);
            }
        }
    }

    protected void persistOrderStatus(TaskOrder order){
        order.setPluginStatus(2);
        int frequency =order.getFrequency();
        frequency++;
        order.setFrequency(frequency);
        orderRepository.save(order);
    }
    /**
     * 这种属于特殊情况，需要审核后再进行处理。讨论需求后在来进行处理。暂时使用第一种情况。
     *  1.再次分配，第一次任务没有分配完成，根据分配了数量的柜台生成任务
     *  2.分配数量0的柜台，不生成任务
     */

}
