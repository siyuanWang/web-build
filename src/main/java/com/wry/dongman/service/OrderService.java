package com.wry.dongman.service;

import com.wry.dongman.dao.OrderMapper;
import com.wry.dongman.domain.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderMapper orderMapper;

    public List<OrderEntity> queryAll() {
        List<OrderEntity> orders = orderMapper.queryAll();

        return orders;
    }

    public List<OrderEntity> queryByUserId(long userId) {
        List<OrderEntity> orders = orderMapper.queryByUserId(userId);

        return orders;
    }

    public int insertOrder(OrderEntity entity) {
        entity.setCtime(new Date());
        return orderMapper.insert(entity);
    }
}
