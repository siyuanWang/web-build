package com.wry.dongman.controller;

import com.wry.dongman.domain.SkuEntity;
import com.wry.dongman.domain.UserVo;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        LOGGER.info("SkuController初始化完毕");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(Model model, HttpServletRequest request) {
        if (CommonUtil.isRoot(request)) {
            List<SkuEntity> list = skuService.queryAll();
            model.addAttribute("list", list);
            return "rootskulist";
        }
        model.addAttribute("list", skuService.queryAllUp());
        return "skulist";
    }
    @RequestMapping(value = "/tosave", method = RequestMethod.GET)
    public String toSave(Model model) {

        return "skuinput";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSku(SkuEntity entity, Model model) {
        skuService.insertSku(entity);
        return "redirect:/sku/list";
    }

    @RequestMapping(value = "/up/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String up(@PathVariable("id") Long id, Model model) {
        skuService.up(id);
        return CommonUtil.successResponse();
    }

    @RequestMapping(value = "/down/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String down(@PathVariable("id") Long id, Model model) {
        skuService.down(id);
        return CommonUtil.successResponse();
    }

    /**
     * 调整库存
     * @param id
     * @param num
     * @param model
     * @return
     */
    @RequestMapping(value = "/purchase/{id}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public String purchase(@PathVariable("id") Long id, @PathVariable("num") Integer num, Model model) {
        if (skuService.purchase(id, num) ==  200) {
            return CommonUtil.successResponse();
        } else {
            return CommonUtil.failResponse("商品不在线");
        }
    }



}
