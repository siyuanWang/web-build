package com.wsy.webseed.dao;

import com.wsy.webseed.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


public interface DbQueryMapper extends ISqlMapper {

    Map<String, Object> queryById(@Param("id") long id);
}
