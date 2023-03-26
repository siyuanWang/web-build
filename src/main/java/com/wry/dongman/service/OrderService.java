package com.wry.dongman.service;

import com.wry.dongman.common.exception.BussinessException;
import com.wry.dongman.dao.OrderMapper;
import com.wry.dongman.domain.OrderEntity;
import com.wry.dongman.domain.OrderVo;
import com.wry.dongman.domain.SkuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SkuService skuService;

    public List<OrderVo> queryAll() {
        List<OrderEntity> orders = orderMapper.queryAll();

        return entityToVos(orders);
    }

    private List<OrderVo> entityToVos(List<OrderEntity> orders) {
        List<OrderVo> ret = new ArrayList<>();
        orders.forEach(x -> {
            OrderVo vo = new OrderVo();
            vo.setId(x.getId());
            vo.setSkuId(x.getSkuId());
            SkuEntity sku = skuService.queryById(x.getSkuId());
            if (sku != null) {
                vo.setSkuName(sku.getName());
            }
            vo.setPosition(x.getPosition());
            vo.setUserId(x.getUserId());
            vo.setCtime(x.getCtime());
            vo.setUtime(x.getUtime());
            ret.add(vo);
        });

        return ret;
    }

    public List<OrderVo> queryByUserId(long userId) {
        List<OrderEntity> orders = orderMapper.queryByUserId(userId);

        return entityToVos(orders);
    }

    public int insertOrder(OrderEntity entity) {
        int code = skuService.sale(entity.getSkuId(), 1);
        if (code == 0) {
            LOGGER.info("sku下架或不存在");
            throw new BussinessException("sku下架或不存在", 0);
        } else if (code == 1) {
            LOGGER.info("sku数量不足");
            throw new BussinessException("sku数量不足", 1);
        } else {
            entity.setCtime(new Date());
            return orderMapper.insert(entity);
        }
    }
}
