package com.wsy.webseed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("indexController初始化完毕");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

}
