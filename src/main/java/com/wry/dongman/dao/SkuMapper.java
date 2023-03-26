package com.wry.dongman.dao;

import com.wry.dongman.dao.base.ISqlMapper;
import com.wry.dongman.domain.SkuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SkuMapper extends ISqlMapper {

    SkuEntity queryById(@Param("id") long id);

    List<SkuEntity> queryAll();

    List<SkuEntity> queryAllUp();

    int insert(@Param("sku") SkuEntity sku);

    int up(Long id);

    int down(Long id);

    int sale(@Param("id") Long id, @Param("num") int num);

    int purchase(@Param("id") Long id, @Param("num") int num);
}
