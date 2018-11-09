package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.ResumeMapper;
import com.frisk.hrs.pojo.Resume;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.service.ResumeService;
import com.frisk.hrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/11
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @Autowired
    @Qualifier("resumeMapper")
    private ResumeMapper resumeMapper;

    @Override
    public Resume findResumeById(Integer id) throws Exception {
        Resume resume = resumeMapper.findResumeByIdBase(id);
        return resume;
    }

    @Override
    public Integer insertResume(Resume resume) throws Exception {
        Integer line = resumeMapper.insertResume(resume);
        return line;
    }

    @Override
    public Integer updateResume(Resume resume) throws Exception {
        Integer line = resumeMapper.updateResume(resume);
        return line;
    }

    @Override
    public Resume findResumeByUserId(Integer id) throws Exception {
        UserCustom userCustom = userService.getUserById(id);
        Resume resume = userCustom.getResume();
        return resume;
    }
}
