package com.dh.springbootintegration.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@Api(hidden = true)
public class FreemarkerController {
    @RequestMapping("/test")
    @ApiOperation(value = "测试",hidden = true)
    public String toBookIndexPage(ModelMap model){
        log.info("进来啦！！！");
        model.put("name","浩哥");
        return "/test/index";
    }
}
