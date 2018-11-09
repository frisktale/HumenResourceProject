package com.frisk.hrs.controller;

import com.frisk.hrs.exception.CustomException;
import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/11
 */
@Controller
@RequestMapping("user")
public class UserPrivateController {

    @Autowired
    @Qualifier("resumeServiceImpl")
    private ResumeService resumeService;

    @Autowired
    @Qualifier("offerServiceImpl")
    private OfferService offerService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("interviewServiceImpl")
    private InterviewService interviewService;



    @RequestMapping("showOffers")
    public ModelAndView showOffers(HttpSession session) {
        ModelAndView mav = new ModelAndView("showOfferToUser");
        User user = (User) session.getAttribute("user");
        Resume resume = null;
        List<OffersCustom> offers = null;
        try {
            offers = offerService.getAllOffers();
            resume = resumeService.findResumeByUserId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("offers", offers);
        mav.addObject("resume", resume);
        return mav;
    }

    @ResponseBody
    @RequestMapping("submitResumePage")
    public String submitResumePage(Integer oId ,HttpSession session){
        User user = (User) session.getAttribute("user");
        Resume resume = null;
        try {
            resume = resumeService.findResumeByUserId(user.getId());
            Integer line = interviewService.submitResume(oId, user.getId(), resume.getId());
            if (line == 0){
                return "已投递过简历";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "投递成功";
    }

    @RequestMapping("addResumePage")
    public ModelAndView addResumePage(HttpSession session){
        User user = (User) session.getAttribute("user");
        Resume resume = null;
        try {
            resume = resumeService.findResumeByUserId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resume!=null){
            return new ModelAndView("redirect:/user/updateResumePage");
        }
        ModelAndView mav = new ModelAndView("resumePage");
        mav.addObject("title","添加简历");
        mav.addObject("subMethod","addResume");
        return mav;
    }

    @RequestMapping(value = "addResume",method = RequestMethod.POST)
    public String addResume(Resume resume,HttpSession session){
        User user = (User) session.getAttribute("user");
        resume.setUserId(user.getId());
        System.out.println(resume);
        try {
            resumeService.insertResume(resume);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/addResumePage";
    }


    @RequestMapping("updateResumePage")
    public ModelAndView resumePage(HttpSession session){
        User user = (User) session.getAttribute("user");
        Resume resume = null;
        try {
            resume = resumeService.findResumeByUserId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resume==null){
            return new ModelAndView("redirect:/user/addResumePage");
        }
        ModelAndView mav = new ModelAndView("resumePage");
        mav.addObject("resume",resume);
        mav.addObject("title","修改简历");
        mav.addObject("subMethod","updateResume");
        return mav;
    }

    @RequestMapping(value = "updateResume",method = RequestMethod.POST)
    public String updateResume(Resume resume,HttpSession session){
        User user = (User) session.getAttribute("user");
        resume.setUserId(user.getId());
        System.out.println(resume);
        try {
            resumeService.updateResume(resume);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/updateResumePage";
    }
    @RequestMapping("userCenter")
    public ModelAndView userCenter(HttpSession session){
        ModelAndView mav = new ModelAndView("touristPage");
        User user = (User) session.getAttribute("user");
        List<InterviewCustom> interview = null;
        try {
            interview = userService.getInterviewWithNotification(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("interviews",interview);
        return mav;
    }


    @RequestMapping("aodInterview")
    public String agreeOrDisagreeInterview(InterviewCustom interviewCustom){
//        System.out.println(interviewCustom);
        try {
            interviewCustom.getInterview().setIsRead(false);
            interviewService.updateInterview(interviewCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/userCenter";
    }

//    {"oldPassword":"123","newPassword":"123","reNewPassword":"123"}

    @ResponseBody
    @RequestMapping("updatePass")
    public String updatePass(HttpSession session,String oldPassword,String newPassword,String reNewPassword){
        User user = (User) session.getAttribute("user");
        if (!user.getPassword().equals(oldPassword)){
            return "密码输入不正确";
        }
        if (!newPassword.equals(reNewPassword)){
            return "两次密码输入不相同";
        }
        Integer row = null;
        try {
            row = userService.updatePassword(user.getId(), newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row==1){
            session.setAttribute("user",null);
            return "密码修改成功";
        }else {
            return "密码修改失败";
        }
    }

    @ResponseBody
    @RequestMapping("attest")
    public String attest(HttpSession session,Integer employeeId,String name){
        User user = (User) session.getAttribute("user");
        Integer row = null;
        try {
            row = userService.attestUser(user.getId(), employeeId, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row==1){
            session.setAttribute("user",null);
            return "认证成功";
        }else {
            return "认证失败,请重新检查输入的数据";
        }
    }

}
