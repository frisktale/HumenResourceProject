package com.frisk.hrs.service;

import com.frisk.hrs.pojo.Resume;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/11
 */
public interface ResumeService {
    public Resume findResumeById(Integer id) throws Exception;

    public Integer insertResume(Resume resume) throws Exception;

    public Integer updateResume(Resume resume) throws Exception;

    public Resume findResumeByUserId(Integer id) throws Exception;

}
