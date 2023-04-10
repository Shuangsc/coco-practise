package com.coco.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.coco.pojo.OrderPojo;

@Mapper
public interface OrderDao extends BaseMapper<OrderPojo> {
}
