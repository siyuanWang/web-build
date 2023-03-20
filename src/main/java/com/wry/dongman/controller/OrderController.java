package com.wry.dongman.controller;

import com.wry.dongman.domain.OrderEntity;
import com.wry.dongman.service.OrderService;
import com.wry.dongman.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("OrderController初始化完毕");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String toOrder(Model model, HttpServletRequest request) {
        long userId = CommonUtil.getUserId(request);
        LOGGER.info("查询用户{}的订单", userId);
        model.addAttribute("list", orderService.queryByUserId(userId));
        return "rootorderlist";
    }

    @RequestMapping(value = "/rootorder", method = RequestMethod.GET)
    public String toOrderRoot(Model model) {
        model.addAttribute("list", orderService.queryAll());
        return "orderlist";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrder(OrderEntity entity, Model model) {
        orderService.insertOrder(entity);
        return "redirect:/order/list";
    }

}
