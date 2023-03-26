package com.wry.dongman.controller;

import com.wry.dongman.common.exception.BussinessException;
import com.wry.dongman.domain.OrderEntity;
import com.wry.dongman.domain.SkuEntity;
import com.wry.dongman.service.OrderService;
import com.wry.dongman.service.SkuService;
import com.wry.dongman.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private SkuService skuService;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("OrderController初始化完毕");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toOrder(Model model, HttpServletRequest request) {
        if (CommonUtil.isRoot(request)) {//管理员
            model.addAttribute("list", orderService.queryAll());
            return "rootorderlist";
        } else {
            long userId = CommonUtil.getUserId(request);
            LOGGER.info("查询用户{}的订单", userId);
            model.addAttribute("list", orderService.queryByUserId(userId));
            return "orderlist";
        }
    }

    @RequestMapping(value = "/toSave/{skuId}", method = RequestMethod.GET)
    public String toSave(@PathVariable long skuId, Model model) {
        SkuEntity sku = skuService.queryById(skuId);
        if (sku == null || sku.getNum() == 0) {
            LOGGER.info("商品已售空");
            return "redirect:/sku/list";
        }
        model.addAttribute("sku", sku);
        return "orderadd";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrder(OrderEntity entity, HttpServletRequest request, Model model) {
        entity.setUserId(CommonUtil.getUserId(request));
        try {
            orderService.insertOrder(entity);
            return "redirect:/order/list";
        } catch (BussinessException e) {
            model.addAttribute("errorInfo", e.getMessage());
            return "orderadd";
        } catch (Exception e) {
            LOGGER.error("saveOrder error", e);
            return "orderadd";
        }
    }

}
