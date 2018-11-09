package com.frisk.hrs.controller;

import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@Controller
@RequestMapping("emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("trainServiceImpl")
    private TrainService trainService;
    @Autowired
    @Qualifier("attendanceServiceImpl")
    private AttendanceService attendanceService;

    @Autowired
    @Qualifier("rewardsServiceImpl")
    private RewardsService rewardsService;

    @Autowired
    private SalaryService salaryService;




    @RequestMapping("employeeCenter")
    public ModelAndView employeeCenter(HttpSession session) {
        ModelAndView mav = new ModelAndView("employeePage");
        User user = (User) session.getAttribute("user");

        UserCustom userCustom = null;
        try {

            userCustom = userService.getUserById(user.getId());
            session.setAttribute("employee", userCustom.getEmployee());
            Integer empId = userCustom.getEmployee().getId();
            EmployeeCustom emp = employeeService.getEmpById(empId);
            List<TrainCustom> trains = trainService.getUnHappenedTrainByDepIdAndPosId(emp.getDepartment().getId(), emp.getPosition().getId());
            Attendance attendance = attendanceService.findAttendanceByDayAndEmployeeId(new Date(), empId);
            mav.addObject("attendance", attendance);
            mav.addObject("trains", trains);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }


    @RequestMapping("showEmp")
    public ModelAndView showEmp(HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        EmployeeCustom emp = null;
        List<EmployeeCustom> employees = null;
        try {
            emp = employeeService.getEmpById(employee.getId());
            Department department = emp.getDepartment();
            employees = employeeService.getEmpByDepId(department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        ModelAndView mav = new ModelAndView("showEmpToEmployee");
        mav.addObject("employees", employees);
        return mav;
    }


    @RequestMapping("startCheck")
    public String startCheck(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        try {
            Integer line = attendanceService.startCheck(new Date(), employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/emp/employeeCenter";
    }

    @RequestMapping("offCheck")
    public String offCheck(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        try {
            Integer line = attendanceService.offCheck(new Date(), employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/emp/employeeCenter";
    }

    @RequestMapping("showAttendance")
    public ModelAndView showAttendance(Date date, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        List<Attendance> attendanceList = null;
        try {
            attendanceList = attendanceService.findAttendanceByMonthAndEmployeeId(date, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("empAtendance");
        mav.addObject("attendances", attendanceList);
        return mav;
    }

    @RequestMapping("showRewards")
    public ModelAndView showRewards(Date date, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        List<Rewards> rewardList = null;
        try {
            rewardList = rewardsService.findRewardsByEmpIdAndMonth(employee.getId(), date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("empRewards");
        mav.addObject("rewards", rewardList);
        return mav;
    }

    @ResponseBody
    @RequestMapping("getSalary")
    public Salary getSalary(Date month, Integer empId, HttpSession session) {
        System.out.println(month);
        if (empId == null) {
            empId = ((Employee) session.getAttribute("employee")).getId();

        }
        Salary salary = null;
        try {
            salary = salaryService.getSalaryByEmployeeIdAndMonth(empId, month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salary;
    }

    @ResponseBody
    @RequestMapping("objectionSalary")
    public String objectSalary(ObjectionSalaryCustom objectionSalaryCustom) {
        Integer row = null;
        try {
            row = salaryService.objectionSalary(objectionSalaryCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row != 0 && row != null) {
            return "提交成功";
        } else {
            return "提交失败";
        }
    }
}
