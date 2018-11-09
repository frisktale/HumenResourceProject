package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Interview;
import com.frisk.hrs.pojo.InterviewCustom;
import com.frisk.hrs.pojo.InterviewFind;

import java.util.List;

public interface InterviewMapper {
    public Interview findInterviewByIdBase(Integer id)throws Exception;

    public InterviewCustom findInterviewById(Integer id)throws Exception;

    public List<InterviewCustom> findInterviewByCustomer(InterviewFind InterviewFind)throws Exception;

    public Integer insertInterview(InterviewCustom InterviewCustom)throws Exception;

    public Integer updateInterview(InterviewCustom InterviewCustom)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<InterviewCustom> showDelete()throws Exception;

    public Integer recoverInterview(List<Integer> ids)throws Exception;

    public Integer cleanInterview()throws Exception;
}