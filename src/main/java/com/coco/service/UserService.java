package com.coco.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coco.dao.UserDao;
import com.coco.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void userInsert(int userId,String userName,String email,String phone,String password){

        UserPojo userPojo = new UserPojo(userId,userName,email,phone,password);
        userDao.insert(userPojo);
    }

    public void userDelete(int userId){
        userDao.delete(new QueryWrapper<UserPojo>().eq("userId",userId));
    }

    public void userUpdate(int userId,String userName,String email,String phone,String password){

        userDao.update(new UserPojo(userId,userName,email,phone,password),new QueryWrapper<UserPojo>().eq("userId",userId));
    }

    public List<UserPojo> findAll(){
        return userDao.selectList(new QueryWrapper<UserPojo>());
    }
    public List<UserPojo> findOne(String userName){
        Map<String, Object> map=new HashMap<>();
        map.put("userName",userName);
        return userDao.selectByMap(map);
    }
}
