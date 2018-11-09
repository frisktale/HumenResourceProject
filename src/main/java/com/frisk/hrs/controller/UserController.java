package com.frisk.hrs.controller;

import com.frisk.hrs.pojo.OffersCustom;
import com.frisk.hrs.pojo.User;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.service.OfferService;
import com.frisk.hrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */


@Controller
public class UserController {

    public static final Integer ADMIN = 2;
    public static final Integer EMPLOYEE = 1;
    public static final Integer USER = 0;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @RequestMapping("/")
    public String index(){
        return "redirect:/loginPage";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        UserCustom user = null;
        try {
            user = userService.getUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user.getUser());

            if (UserController.ADMIN.equals(user.getUser().getType())) {
                return "redirect:/admin/adminCenter";
            } else if (UserController.EMPLOYEE.equals(user.getUser().getType())) {
                return "redirect:/emp/employeeCenter";
            } else {
                return "redirect:/user/userCenter";
            }
        }
        return "redirect:/loginPage";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(String username, String password) {
        if ("".equals(username.trim())||"".equals(password.trim())){
            return "redirect:/registerPage";
        }
        User userByName = null;
        try {
            userByName = userService.getUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userByName != null) {
            return "redirect:/registerPage";
        }
        Integer integer = null;
        try {
            integer = userService.insertUser(username.trim(), password.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (integer == 1) {
            return "redirect:/loginPage";
        } else {
            return "redirect:/registerPage";
        }
    }

    @ResponseBody
    @RequestMapping(value = "checkName", method = RequestMethod.POST)
    public String checkName(String name) {
        System.out.println(name);
        User user = null;
        try {
            user = userService.getUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            return "a";
        } else {
            return "n";
        }
    }
}
