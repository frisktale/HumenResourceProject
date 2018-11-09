package com.frisk.hrs.service;

import com.frisk.hrs.pojo.InterviewCustom;
import com.frisk.hrs.pojo.Resume;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 *
 * 拒绝录用
 * 通知面试
 * 录用">
 *
 */
public interface InterviewService {

    public static final String NOT_EMPLOY = "拒绝录用";
    public static final String NOTICE_INTERVIEW = "通知面试";
    public static final String IS_EMPLOY = "录用";
    public static final String ADMIN_NOT_READ = "管理员未读";

    public List<InterviewCustom> findInterviewByOfferId(Integer id) throws Exception;
    public List<InterviewCustom> findInterviewByOfferIdAndUserId(Integer oId,Integer uId) throws Exception;

    public InterviewCustom findInterviewById(Integer id) throws Exception;

    public Integer updateInterview(InterviewCustom interviewCustom) throws Exception;

    public Integer addInterview(InterviewCustom interviewCustom) throws Exception;

    public Integer submitResume(Integer oId, Integer uId, Integer rId) throws Exception;

    public Integer hire(Integer inteId,Double salary,String status) throws Exception;
}
