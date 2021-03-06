package com.imc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
     * @return 1
     */
    @RequestMapping("/html1")
    public ModelAndView html1(Model model) {
        ModelAndView modelAndView = new ModelAndView("html/test1");
        model.addAttribute("str","我是从后台传过来的");

        Map<String, String> map = new HashMap<>();
        map.put("mKey", "mValue");
        model.addAttribute("obj",map);
        return modelAndView;
    }

    /**
     * 直接返回test1.html
     * @return
     */
    @RequestMapping("/html2")
    public String html2() {
        return "html/test1";
    }

    /**
     * 返回testJsp
     * @return
     */
    @RequestMapping("/jsp1")
    public String jsp1(Model model) {
        model.addAttribute("background", "this is index jsp page");
        return "jsp/testJsp";
    }
}
