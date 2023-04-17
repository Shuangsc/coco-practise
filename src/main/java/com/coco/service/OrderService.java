package com.coco.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coco.dao.OrderDao;
import com.coco.pojo.OrderPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    public void orderInsert(int orderId, int userId,String type,double amount,String date,String description){

        OrderPojo orderPojo = new OrderPojo(orderId,userId,type,amount,date,description);
        orderDao.insert(orderPojo);
    }

    public void orderDelete(int orderId){
        orderDao.delete(new QueryWrapper<OrderPojo>().eq("orderId",orderId));
    }

    public void orderUpdate(int orderId, int userId,String type,double amount,String date,String description){

        orderDao.update(new OrderPojo(orderId,userId,type,amount,date,description),new QueryWrapper<OrderPojo>().eq("orderId",orderId));
    }

    public List<OrderPojo> findAll(){
        return orderDao.selectList(new QueryWrapper<OrderPojo>());
    }

    public List<OrderPojo> findByUserId(Integer userId){
        QueryWrapper<OrderPojo> queryWrapper = new QueryWrapper<OrderPojo>();
        queryWrapper.eq("userId",userId);
        return orderDao.selectList(queryWrapper);
    }

    public List<OrderPojo> findIncomeList(int userId) {
        QueryWrapper<OrderPojo> queryWrapper = new QueryWrapper<OrderPojo>();
        queryWrapper.gt("amount",0);
        queryWrapper.eq("userId",userId);
        return orderDao.selectList(queryWrapper);
    }

    public List<OrderPojo> findoutComeList(int userId) {
        QueryWrapper<OrderPojo> queryWrapper = new QueryWrapper<OrderPojo>();
        queryWrapper.lt("amount",0);
        queryWrapper.eq("userId",userId);
        return orderDao.selectList(queryWrapper);
    }
}
