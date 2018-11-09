package com.frisk.hrs.mapper;

import com.frisk.hrs.pojo.Resume;

import java.util.List;

public interface ResumeMapper {
    public Resume findResumeByIdBase(Integer id) throws Exception;
    public Resume findResumeByUserIdBase(Integer id) throws Exception;

//    public List<Resume> findResumeByCustom()

    public Integer insertResume(Resume ResumeCustom)throws Exception;

    public Integer updateResume(Resume ResumeCustom)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<Resume> showDelete()throws Exception;

    public Integer recoverResume(List<Integer> ids)throws Exception;

    public Integer cleanResume()throws Exception;
}