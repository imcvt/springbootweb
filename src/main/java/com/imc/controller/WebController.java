package com.imc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author luoly
 * @date 2018/12/21 16:24
 * @description 控制层
 */
@Controller
@RequestMapping("/springbootweb")
public class WebController {

    /**
     * modelandview的方式返回test1.html
     * @return
     */
    @RequestMapping("/m1")
    public ModelAndView m1() {
        ModelAndView modelAndView = new ModelAndView("test1");
        return modelAndView;
    }

    /**
     * 直接返回test1.html
     * @return
     */
    @RequestMapping("/m2")
    public String m2() {
        return "test1";
    }
}
