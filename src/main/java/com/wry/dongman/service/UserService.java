package com.wry.dongman.service;

import com.wry.dongman.dao.UserMapper;
import com.wry.dongman.domain.UserEntity;
import com.wry.dongman.domain.UserVo;
import com.wry.dongman.util.CommonUtil;
import com.wry.dongman.util.Constance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    public List<UserVo> queryList() {
        List<UserEntity> list = userMapper.queryAll();
        List<UserVo> ret = new ArrayList<>();
        list.forEach(x -> {
            UserVo vo = new UserVo();
            vo.setId(x.getId());
            vo.setCtime(x.getCtime());
            vo.setUtime(x.getUtime());
            vo.setSex(x.getSex());
            vo.setEmail(x.getEmail());
            vo.setType(x.getType());
            ret.add(vo);
        });
        return ret;
    }

    public int login(String username, String password, HttpServletRequest request) {
        UserEntity entity = userMapper.queryByEmail(username);
        if (entity != null) {
            LOGGER.info("user entity info:{}", entity.toString());
            if (entity.getPassword().equals(password)) {
                CommonUtil.setUserId(request, entity.getId(), entity.getName(), entity.getType());
                return entity.getType();
            }
        }

        LOGGER.info("user info not exist.email:{}", username);

        return Constance.FAIL;
    }

    public boolean register(UserVo userVo) {
        if (!userVo.getPassword().equals(userVo.getPassword2())) {
            LOGGER.info("两次输入密码不一致,password:{},password1:{}", userVo.getPassword(), userVo.getPassword2());
            return false;
        }
        UserEntity entity = new UserEntity();
        entity.setCtime(new Date());
        entity.setEmail(userVo.getEmail());
        entity.setName(userVo.getName());
        entity.setType(Constance.General_User);
        entity.setSex(userVo.getSex());
        entity.setPassword(userVo.getPassword());
        int ret = userMapper.insert(entity);

        return ret == 1;
    }

}
