package com.wry.dongman.controller;

import com.wry.dongman.dao.UserMapper;
import com.wry.dongman.domain.UserVo;
import com.wry.dongman.service.UserService;
import com.wry.dongman.util.Constance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("UserController初始化完毕");
    }

    //登录页面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toLogin() {
        LOGGER.info("to login.{}", System.currentTimeMillis());
        return "login";
    }

    //注册页面
    @RequestMapping(value = "/toregister", method = RequestMethod.GET)
    public String toRegister() {

        return "register";
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        LOGGER.info("username:{},password:{}", username, password);
        int ret = userService.login(username, password);
        if (ret == Constance.Root_User) {//管理员页面
            return "rootlist";
        } else if (ret == Constance.General_User) {//用户页面
            return "list";
        }
        model.addAttribute(Constance.ERROR_INFO, "用户名或密码错误");
        return "login";
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(UserVo userVo, Model model) {
        LOGGER.info("注册用户详情为：{}", userVo);
        if (userService.register(userVo)) {
            return "login";
        }
        model.addAttribute(Constance.ERROR_INFO, "表单数据异常");
        return "register";
    }

}
