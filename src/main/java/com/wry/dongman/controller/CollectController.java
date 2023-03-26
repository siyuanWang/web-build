package com.wry.dongman.controller;

import com.wry.dongman.domain.CollectEntity;
import com.wry.dongman.domain.CollectVo;
import com.wry.dongman.service.CollectService;
import com.wry.dongman.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("CollectController初始化完毕");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        long userId = CommonUtil.getUserId(request);
        List<CollectVo> list = collectService.queryAll(userId);
        model.addAttribute("list", list);
        return "collectlist";
    }

    @RequestMapping(value = "/save/{skuId}", method = RequestMethod.POST)
    @ResponseBody
    public String save(@PathVariable long skuId, HttpServletRequest request, Model model) {
        long userId = CommonUtil.getUserId(request);
        CollectEntity entity = new CollectEntity();
        entity.setUserId(userId);
        entity.setSkuId(skuId);
        if (collectService.insert(entity) == 1) {
            return CommonUtil.successResponse();
        }
        return CommonUtil.failResponse("收藏失败，请刷新页面");
    }

}
