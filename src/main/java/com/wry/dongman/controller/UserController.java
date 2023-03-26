package com.wry.dongman.controller;

import com.wry.dongman.dao.UserMapper;
import com.wry.dongman.domain.UserVo;
import com.wry.dongman.service.UserService;
import com.wry.dongman.util.CommonUtil;
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
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "index";
    }
    //登录页面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toLogin() {
        LOGGER.info("to login.{}", System.currentTimeMillis());
        return "login";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toUserList(Model model) {
        model.addAttribute("list", userService.queryList());
        return "userlist";
    }

    //注册页面
    @RequestMapping(value = "/toregister", method = RequestMethod.GET)
    public String toRegister() {

        return "register";
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model, HttpServletRequest request) {
        LOGGER.info("username:{},password:{}", username, password);
        int ret = userService.login(username, password, request);
        if (ret == Constance.Root_User) {//管理员页面
            return "redirect:/user/index";
        } else if (ret == Constance.General_User) {//用户页面
            return "redirect:/user/index";
        }
        model.addAttribute(Constance.ERROR_INFO, "用户名或密码错误");
        return "login";
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginout(HttpServletRequest request) {
        LOGGER.info("username:{} login out.", CommonUtil.getLoginName(request));
        CommonUtil.logout(request);
        return "redirect:/user/";
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(UserVo userVo, Model model) {
        LOGGER.info("注册用户详情为：{}", userVo);
        if (userService.register(userVo)) {
            return "redirect:/user/";
        }
        model.addAttribute(Constance.ERROR_INFO, "表单数据异常");
        return "register";
    }

}
