package com.wry.dongman.dao;

import com.wry.dongman.dao.base.ISqlMapper;
import com.wry.dongman.domain.CollectEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper extends ISqlMapper {

    List<CollectEntity> queryByUserId(@Param("id") long id);

    int insert(@Param("collect") CollectEntity sku);
}
