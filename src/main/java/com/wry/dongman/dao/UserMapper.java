package com.wry.dongman.dao;

import com.wry.dongman.dao.base.ISqlMapper;
import com.wry.dongman.domain.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends ISqlMapper {

    UserEntity queryById(@Param("id") long id);

    List<UserEntity> queryAll();

    UserEntity queryByEmail(@Param("email") String name);

    int insert(@Param("user") UserEntity user);
}
