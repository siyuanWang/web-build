package com.wry.dongman.service;

import com.wry.dongman.dao.CollectMapper;
import com.wry.dongman.dao.SkuMapper;
import com.wry.dongman.domain.CollectEntity;
import com.wry.dongman.domain.CollectVo;
import com.wry.dongman.domain.SkuEntity;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private SkuMapper skuMapper;

    public List<CollectVo> queryAll(long userId) {
        List<CollectEntity> collects = collectMapper.queryByUserId(userId);
        List<CollectVo> vos = new ArrayList<>();
        for (CollectEntity entity : collects) {
            SkuEntity sku = skuMapper.queryById(entity.getSkuId());
            if (sku != null) {
                CollectVo vo = new CollectVo();
                vo.setId(entity.getId());
                vo.setSkuId(entity.getSkuId());
                vo.setSkuName(sku.getName());
                vo.setCtime(entity.getCtime());
                vo.setUtime(entity.getUtime());
                vos.add(vo);
            }
        }
        return vos;
    }

    public int insert(CollectEntity entity) {
        SkuEntity sku = skuMapper.queryById(entity.getSkuId());
        if (sku == null) {
            LOGGER.warn("sku不存在");
            return 0;
        }
        List<CollectEntity> list = collectMapper.queryByUserIdAndSkuId(entity.getUserId(), entity.getSkuId());
        if (CollectionUtils.isNotEmpty(list)) {
            LOGGER.info("skuId:{}, userId:{}已存在", entity.getSkuId(), entity.getUserId());
            return 1;
        }
        entity.setCtime(new Date());
        return collectMapper.insert(entity);
    }
}
