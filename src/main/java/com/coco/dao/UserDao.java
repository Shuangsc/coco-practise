package com.coco.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.coco.pojo.UserPojo;

@Mapper
public interface UserDao extends BaseMapper<UserPojo>{
}
