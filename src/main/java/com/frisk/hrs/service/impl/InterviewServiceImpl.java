package com.frisk.hrs.service.impl;

import com.frisk.hrs.controller.UserController;
import com.frisk.hrs.mapper.InterviewMapper;
import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    @Qualifier("interviewMapper")
    private InterviewMapper interviewMapper;

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

    @Override
    public List<InterviewCustom> findInterviewByOfferId(Integer id) throws Exception {
        InterviewFind interviewFind = new InterviewFind();
        interviewFind.andOfferIdIdIs(id);
        List<InterviewCustom> interviews = interviewMapper.findInterviewByCustomer(interviewFind);
        return interviews;
    }

    @Override
    public List<InterviewCustom> findInterviewByOfferIdAndUserId(Integer oId, Integer uId) throws Exception {
        InterviewFind interviewFind = new InterviewFind();
        interviewFind.andUserIdIs(uId);
        interviewFind.andOfferIdIdIs(oId);
        List<InterviewCustom> interview = interviewMapper.findInterviewByCustomer(interviewFind);
        return interview;
    }

    @Override
    public InterviewCustom findInterviewById(Integer id) throws Exception {
        InterviewCustom interview = interviewMapper.findInterviewById(id);
        return interview;
    }

    @Override
    public Integer updateInterview(InterviewCustom interviewCustom) throws Exception {

        Integer line = interviewMapper.updateInterview(interviewCustom);
        return line;
    }

    @Override
    public Integer addInterview(InterviewCustom interviewCustom) throws Exception {
        Integer line = interviewMapper.insertInterview(interviewCustom);
        return line;
    }

    @Override
    public Integer submitResume(Integer oId, Integer uId, Integer rId) throws Exception {


        List<InterviewCustom> interviews = findInterviewByOfferIdAndUserId(oId, uId);
        if (interviews.size()>0){
            return 0;
        }
        Interview interview = new Interview();
        interview.setDeliveryTime(new Date());
        interview.setIsRead(false);
        Offers offers = new Offers();
        offers.setId(oId);
        User user = new User();
        user.setId(uId);
        Resume resume = new Resume();
        resume.setId(rId);

        InterviewCustom interviewCustom = new InterviewCustom();
        interviewCustom.setUser(user);
        interviewCustom.setResume(resume);
        interviewCustom.setOffers(offers);
        interviewCustom.setInterview(interview);
        Integer row = interviewMapper.insertInterview(interviewCustom);
        return row;
    }

    @Override
    public Integer hire(Integer inteId,Double salary,String status) throws Exception {
        InterviewCustom interview = null;
        OffersCustom offerById = null;
        Integer row = null;
        interview = interviewService.findInterviewById(inteId);
        offerById = offerService.getOfferById(interview.getOffers().getId());
        Integer depId = offerById.getDepartment().getId();
        Integer id = offerById.getPosition().getId();
        Employee employee = new Employee();
        employee.setName(interview.getResume().getName());
        employee.setSalary(salary);
        employee.setStatus(status);
        Position position = new Position();
        position.setId(offerById.getPosition().getId());
        Department department = new Department();
        department.setId(offerById.getDepartment().getId());
        EmployeeCustom employeeCustom = new EmployeeCustom();
        employeeCustom.setEmployee(employee);
        employeeCustom.setDepartment(department);
        employeeCustom.setPosition(position);
        employeeCustom.setUser(interview.getUser());
        row = employeeService.insertEmp(employeeCustom);
        userService.setType(interview.getUser().getId(), UserController.EMPLOYEE);
        userService.setEmpId(interview.getUser().getId(),employeeCustom.getEmployee().getId());
        Employee employee1 = new Employee();
        interview.setEmployee(employeeCustom.getEmployee());
        interviewService.updateInterview(interview);
        return row;
    }

}
