package com.wry.dongman.service;

import com.wry.dongman.dao.SkuMapper;
import com.wry.dongman.domain.SkuEntity;
import com.wry.dongman.domain.UserEntity;
import com.wry.dongman.domain.UserVo;
import com.wry.dongman.util.Constance;
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
}
