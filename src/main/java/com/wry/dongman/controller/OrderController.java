package com.wry.dongman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("OrderController初始化完毕");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

}
