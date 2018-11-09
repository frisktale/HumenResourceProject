package com.frisk.hrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author frisktale
 * @date 2018/10/8
 */
@Controller
public class PageController {
    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("registerPage")
    public String registerPage(){
        return "register";
    }
}
