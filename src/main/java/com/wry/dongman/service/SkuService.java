package com.wry.dongman.service;

import com.wry.dongman.dao.SkuMapper;
import com.wry.dongman.domain.SkuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SkuService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SkuMapper skuMapper;
    public SkuEntity queryById(long skuId) {
        SkuEntity sku = skuMapper.queryById(skuId);

        return sku;
    }
    public List<SkuEntity> queryAll() {
        List<SkuEntity> skus = skuMapper.queryAll();

        LOGGER.info("queryAll sku size:{}", skus.size());
        return skus;
    }

    public List<SkuEntity> queryAllUp() {
        List<SkuEntity> skus = skuMapper.queryAllUp();

        LOGGER.info("queryAll sku size:{}", skus.size());
        return skus;
    }

    public int insertSku(SkuEntity entity) {
        entity.setCtime(new Date());
        return skuMapper.insert(entity);
    }

    public int up(Long id) {

        return skuMapper.up(id);
    }

    public int down(Long id) {

        return skuMapper.down(id);
    }

    public int sale(long skuId, int num) {
        SkuEntity entity = skuMapper.queryById(skuId);
        //商品不存在 或者 下架
        if (entity == null || entity.getStatus() == 0) return 0;
        //剩余商品数量小于购买数量
        if (entity.getNum() < num) return 1;
        int after = entity.getNum() - num;
        skuMapper.sale(skuId, after);
        return 200;
    }

    public int purchase(long skuId, int num) {
        SkuEntity entity = skuMapper.queryById(skuId);
        if (entity == null) return 0;
        skuMapper.purchase(skuId, num);
        return 200;
    }
}
