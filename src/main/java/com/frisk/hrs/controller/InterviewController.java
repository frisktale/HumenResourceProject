package com.frisk.hrs.controller;

import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@RequestMapping("interview")
@Controller
public class InterviewController {

    @Autowired
    @Qualifier("resumeServiceImpl")
    private ResumeService resumeService;

    @Autowired
    @Qualifier("offerServiceImpl")
    private OfferService offerService;

    @Autowired
    @Qualifier("interviewServiceImpl")
    private InterviewService interviewService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @RequestMapping("showOffer")
    public ModelAndView showOffer(){
        ModelAndView mav = new ModelAndView("offerList");
        List<DepartmentCustom> allDepartment = null;
        List<OffersCustom> offers = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            offers = offerService.getAllOffers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("offers",offers);
        mav.addObject("department",allDepartment);
        return mav;
    }



    @ResponseBody
    @RequestMapping("addOffer")
    public String addOffer(OffersCustom offersCustom) {
        Integer row = null;
        try {
            row = offerService.addOffer(offersCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }


    @ResponseBody
    @RequestMapping("updateOffer")
    public String updateOffer(OffersCustom offersCustom) {
        Integer row = null;
        try {
            row = offerService.updateOffer(offersCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @ResponseBody
    @RequestMapping("deleteOffer")
    public String deleteOffer(Integer[] ids){
        if (ids==null||ids.length==0){
            return "删除失败";
        }
        List<Integer> list = Arrays.asList(ids);
        Integer row = null;
        try {
            row = offerService.deleteOffers(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row != 0) {
            return "删除了"+row+"个招聘信息";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("showInterviewByOId")
    public ModelAndView showInterviewById(Integer oId){
        ModelAndView mav = new ModelAndView("interviewrList");
        List<InterviewCustom> interview = null;
        try {
            interview = interviewService.findInterviewByOfferId(oId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("interviews",interview);
        return mav;
    }
    @RequestMapping("updateInterviewPage")
    public ModelAndView updateInterviewPage(Integer id){
        ModelAndView mav = new ModelAndView("updateInterviewPage");
        InterviewCustom interview = null;
        try {
            interview = interviewService.findInterviewById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("interview",interview);
        return mav;
    }

    @ResponseBody
    @RequestMapping("hire")
    public String hire(Integer inteId,Double salary,String status){
        InterviewCustom interview = null;
        OffersCustom offerById = null;
        Integer row = null;
        try {
            row = interviewService.hire(inteId, salary, status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (row==1){
            return "成功";
        }else {
            return "失败";
        }

    }


    @ResponseBody
    @RequestMapping("updateInterview")
    public String updateInterview(InterviewCustom interviewCustom){
        interviewCustom.getInterview().setUserStatus(UserService.USER_NOT_READ);
        interviewCustom.getInterview().setIsRead(true);
        System.out.println(interviewCustom);
        try {
            interviewService.updateInterview(interviewCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "操作成功";
    }

    @ResponseBody
    @RequestMapping("closeInterview")
    public String closeInterview(InterviewCustom interviewCustom){
        interviewCustom.getInterview().setStatus(null);
        interviewCustom.getInterview().setUserStatus(null);
        interviewCustom.getInterview().setIsRead(true);
        System.out.println(interviewCustom);
        try {
            interviewService.updateInterview(interviewCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "操作成功";
    }

    @RequestMapping(value = "userResume")
    public ModelAndView showResume(Integer uId){
        Resume resume = null;
        try {
            resume = resumeService.findResumeByUserId(uId);
            System.out.println(resume);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showResume");
        mav.addObject("resume",resume);
        return mav;
    }
}
