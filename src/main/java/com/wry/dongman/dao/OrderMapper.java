package com.wry.dongman.dao;

import com.wry.dongman.dao.base.ISqlMapper;
import com.wry.dongman.domain.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderMapper extends ISqlMapper {

    OrderEntity queryById(@Param("id") long id);

    List<OrderEntity> queryAll();

    List<OrderEntity> queryByUserId(@Param("userId") long userId);

    int insert(@Param("order") OrderEntity order);
}
