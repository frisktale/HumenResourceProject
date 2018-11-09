package com.frisk.hrs.controller;

import com.frisk.hrs.exception.CustomException;
import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.*;
import com.sun.org.glassfish.gmbal.ParameterNames;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/12
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    public static final int SHOW_EMP_BY_POS = 0;
    public static final int SHOW_EMP_BY_Dep = 1;
    public static final int SHOW_TRAIN_BY_POS = 0;
    public static final int SHOW_TRAIN_BY_Dep = 1;


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("attendanceServiceImpl")
    private AttendanceService attendanceService;

    @Autowired
    @Qualifier("rewardsServiceImpl")
    private RewardsService rewardsService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private TrainService trainService;

    @RequestMapping("adminCenter")
    public ModelAndView adminCenter() {
        ModelAndView mav = new ModelAndView("adminPage");
        List<SalaryCustom> unProcessObjectionSalary = null;
        List<DepartmentCustom> allDepartment = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            mav.addObject("department", allDepartment);
            unProcessObjectionSalary = salaryService.getUnProcessObjectionSalary();
            mav.addObject("objection", unProcessObjectionSalary);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }


    @RequestMapping("showAttendance")
    public ModelAndView showAttendance(Date date, Integer empId) {
        List<Attendance> attendanceList = null;
        try {
            attendanceList = attendanceService.findAttendanceByMonthAndEmployeeId(date, empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showEmpAtendanceToAdmin");
        mav.addObject("attendances", attendanceList);
        return mav;
    }

    @RequestMapping("showRewards")
    public ModelAndView showRewards(Date date, Integer empId) {
        List<Rewards> rewardList = null;
        try {
            rewardList = rewardsService.findRewardsByEmpIdAndMonth(empId, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showEmpRewardsToAdmin");
        mav.addObject("rewards", rewardList);
        mav.addObject("empId", empId);
        return mav;
    }


    @RequestMapping("addRewardsPage")
    public ModelAndView addRewardsPage(Integer empId)  {
        ModelAndView mav = new ModelAndView("addRewards");
        mav.addObject("employeeId", empId);
        mav.addObject("method", "addReward");
        return mav;
    }

    @RequestMapping("updateRewardsPage")
    public ModelAndView updateRewardsPage(Integer id)  {
        ModelAndView mav = new ModelAndView("addRewards");
        Rewards reward = null;
        try {
            reward = rewardsService.findRewardById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("employeeId", reward.getEmployeeId());
        mav.addObject("reward", reward);
        mav.addObject("method", "updateReward");
        return mav;
    }


    @ResponseBody
    @RequestMapping("updateReward")
    public String updateReward(Rewards rewards) {
        Integer row = null;
        try {
            row = rewardsService.updateReward(rewards);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row != 1) {
            return "更新失败";
        }
        return "更新成功";
    }
    @ResponseBody
    @RequestMapping("addReward")
    public String addReward(Rewards rewards) {
        Integer row = null;
        try {
            row = rewardsService.addReward(rewards);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row != 1) {
            return "更新失败";
        }
        return "更新成功";
    }

    @ResponseBody
    @RequestMapping("deleteReward")
    public String deleteReward(Integer[] ids){
        if (ids==null||ids.length==0){
            return "删除失败";
        }
        Integer row = null;
        try {
            row = rewardsService.deleteRewardsByIds(Arrays.asList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return "删除失败";
        }
        return "删除了" + row + "条奖惩";
    }

    @ResponseBody
    @RequestMapping("updateDepartment")
    public String updateDepartment(DepartmentCustom departmentCustom) {
        Integer line = null;
        try {
            line = departmentService.updateDepartment(departmentCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (line == 1) {
            return "修改成功";
        } else {
            return "修改失败,部门已存在名";
        }
    }


    @ResponseBody
    @RequestMapping("addPosition")
    public String addPosition(String posName, Integer depId) {
        if ((posName==null||"".equals(posName.trim()))||depId==null){
            return "添加失败";
        }
        Integer line = null;
        try {
            line = positionService.addPosition(depId, posName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (line == 1) {
            return "添加成功";
        } else {
            return "添加失败,部门已存在名";
        }
    }

    @ResponseBody
    @RequestMapping("computeSalary")
    public String computeSalary(Integer empId, Date date, Double performance) {
        Salary salary = null;
        try {
            salary = salaryService.computeSalary(empId, date, performance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "结算成功";
    }

    @RequestMapping("computeSalaryPage")
    public ModelAndView computeSalaryPage(Integer empId) {
        ModelAndView mav = new ModelAndView("computeSalary");
        mav.addObject("empId",empId);
        return mav;
    }

    @RequestMapping("showEmp")
    public ModelAndView showEmp(Integer id, Integer type) {
        List<EmployeeCustom> emp = null;
        try {
            switch (type) {
                case AdminController.SHOW_EMP_BY_POS:
                    emp = employeeService.getEmpByPosId(id);
                    break;
                case AdminController.SHOW_EMP_BY_Dep:
                    emp = employeeService.getEmpByDepId(id);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showEmp");
        mav.addObject("employees", emp);
        return mav;
    }

    @RequestMapping("showAllEmp")
    public ModelAndView showAllEmp() {
        List<EmployeeCustom> emp = null;
        try {
            emp = employeeService.getAllEmp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showEmp");
        mav.addObject("employees", emp);
        return mav;
    }

    @ResponseBody
    @RequestMapping("addEmployee")
    public String addEmployee(EmployeeCustom employeeCustom) throws CustomException {
        Integer line = null;
        try {
            line = employeeService.insertEmp(employeeCustom);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (line == 1) {
            return "添加成功";
        } else {
            throw new CustomException("添加员工失败");
        }
    }

    @RequestMapping("addEmployeePage")
    public ModelAndView addEmployeePage() {
        List<DepartmentCustom> allDepartment = null;
        EmployeeCustom emp = null;
        try {
            allDepartment = departmentService.getAllDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("updateEmp");
        mav.addObject("department", allDepartment);
        mav.addObject("method", "admin/addEmployee");
        mav.addObject("title", "添加员工");
        return mav;
    }

    @RequestMapping("updatePage")
    public ModelAndView updatePage(Integer id) {
        ModelAndView mav = new ModelAndView("updateEmp");
        List<DepartmentCustom> allDepartment = null;
        EmployeeCustom emp = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            emp = employeeService.getEmpById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("department", allDepartment);
        mav.addObject("employee", emp);
        mav.addObject("method", "admin/update");
        mav.addObject("title", "修改员工信息");

        return mav;
    }

    @ResponseBody
    @RequestMapping("update")
    public String updateEmp(EmployeeCustom employeeCustom) {
        System.out.println(employeeCustom);
        try {
            employeeService.updateEmp(employeeCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "更新成功";
    }

    @ResponseBody
    @RequestMapping("deleteEmployee")
    public String deleteEmployee(Integer[] ids){
        if (ids==null||ids.length==0){
            return "删除失败";
        }
        List<Integer> list = Arrays.asList(ids);
        Integer row = null;
        try {
            row = employeeService.deleteEmployee(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return "删除失败";
        }
        return "删除了" + row + "条员工信息";
    }


    @RequestMapping("showDeleteEmployee")
    public ModelAndView showDeleteEmployee(){
        List<EmployeeCustom> emp = null;
        try {
            emp = employeeService.showDeleteEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("deleteEmployee");
        mav.addObject("employees", emp);
        return mav;
    }

    @ResponseBody
    @RequestMapping("recoverEmployee")
    public String recoverEmployee(Integer[] ids){
        if (ids==null||ids.length==0){
            return "恢复失败";
        }
        List<Integer> list = Arrays.asList(ids);
        Integer row = null;
        try {
            row = employeeService.recoverDeleteEmployee(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return "恢复失败";
        }
        return "恢复了" + row + "条员工信息";
    }




    @ResponseBody
    @RequestMapping("cleanEmployee")
    public String cleanEmployee(){
        Integer row = null;
        try {
            row = employeeService.cleanDeleteEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "清空了" + row + "条员工信息";
    }


    @RequestMapping("showEmpBySalary")
    public ModelAndView showEmp(Integer id) {
        List<EmployeeCustom> emp = new ArrayList<>();
        EmployeeCustom empBySalaryId = null;
        try {
            empBySalaryId = employeeService.getEmpBySalaryId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emp.add(empBySalaryId);
        ModelAndView mav = new ModelAndView("showEmp");
        mav.addObject("employees", emp);
        return mav;
    }

    @ResponseBody
    @RequestMapping("processObjectionSalary")
    public String processObjectionSalary(Integer salaryId, Integer objId, Double money, Boolean isExecute) {
        Integer row = null;
        try {
            row = salaryService.processObjectionSalary(salaryId, objId, money, isExecute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 1) {
            return "处理成功";
        } else {
            return "处理失败";
        }
    }

    @RequestMapping("depList")
    public ModelAndView depList() {
        ModelAndView mav = new ModelAndView("departmentList");
        List<SalaryCustom> unProcessObjectionSalary = null;
        List<DepartmentCustom> allDepartment = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            mav.addObject("department", allDepartment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @ResponseBody
    @RequestMapping("addDepartment")
    public String addDepartment(DepartmentCustom departmentCustom) {
        String name = departmentCustom.getDepartment().getName();
        if (name==null||"".equals(name.trim())){
            return "添加失败";
        }
        Integer line = null;
        try {
            line = departmentService.addDepartment(departmentCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (line == 1) {
            return "添加成功";
        } else {
            return "添加失败,部门已存在";
        }
    }

    @ResponseBody
    @RequestMapping("deleteDep")
    public String deleteDep(Integer[] ids) {
        if (ids==null||ids.length==0){
            return "删除失败";
        }
        Integer row = null;
        System.out.println(ids.length);
        try {
            List<Integer> list = Arrays.asList(ids);
            System.out.println(list.size());
            row = departmentService.deleteDepartment(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return "所选部门还有员工,不能删除";
        }
        return "删除了" + row + "个部门";
    }

    @ResponseBody
    @RequestMapping("updateDep")
    public String updateDep(DepartmentCustom departmentCustom) {
        Integer row = null;
        try {
            row = departmentService.updateDepartment(departmentCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 1) {
            return "修改成功";
        } else {
            return "修改失败,部门名已存在";
        }
    }

    @RequestMapping("posList")
    public ModelAndView posList(Integer depId){
        ModelAndView mav = new ModelAndView("positionList");
        List<SalaryCustom> unProcessObjectionSalary = null;
        DepartmentCustom department = null;
        List<DepartmentCustom> allDepartment = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            mav.addObject("allDepartment", allDepartment);
            department = departmentService.getDepartmentById(depId);
            mav.addObject("department", department);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @ResponseBody
    @RequestMapping("updatePos")
    public String updatePos(PositionCustom positionCustom){
        Integer row = null;
        try {
            row = positionService.updatePosition(positionCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row==1){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @ResponseBody
    @RequestMapping("deletePos")
    public String deletePos(Integer[] ids) {
        if (ids==null||ids.length==0){
            return "删除失败";
        }
        Integer row = null;
        try {
            List<Integer> list = Arrays.asList(ids);
            System.out.println(list.size());
            row = positionService.deletePosition(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return "所选职位还有员工,不能删除";
        }
        return "删除了" + row + "个职位";
    }

    @RequestMapping("showTrain")
    public ModelAndView showTrain(Integer id, Integer type) {

        List<DepartmentCustom> allDepartment = null;

        List<TrainCustom> trains = null;
        try {
            allDepartment = departmentService.getAllDepartment();
            switch (type) {
                case SHOW_TRAIN_BY_POS:
                    trains = trainService.getTrainsByPositionId(id);
                    break;
                case SHOW_TRAIN_BY_Dep:
                    trains = trainService.getTrainsByDepId(id);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("showTrain");
        mav.addObject("trains", trains);
        mav.addObject("department", allDepartment);
        return mav;
    }

    @ResponseBody
    @RequestMapping("addTrain")
    public String addTrain(TrainCustom trainCustom) {
        Integer row = null;
        try {
            row = trainService.addTrain(trainCustom);
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
    @RequestMapping("updateTrain")
    public String updateTrain(TrainCustom trainCustom){
        Integer row = null;
        try {
            row = trainService.updateTrain(trainCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 1) {
            return "更改成功";
        } else {
            return "更改失败";
        }
    }

    @ResponseBody
    @RequestMapping("deleteTrain")
    public String deleteTrain(Integer[] ids){

        if (ids==null||ids.length==0){
            return "删除失败";
        }
        Integer row = null;
        List<Integer> list = Arrays.asList(ids);
        try {
            row = trainService.deleteTrain(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row != 0&&row!=null) {
            return "删除"+row+"条数据";
        } else {
            return "删除失败";
        }
    }

}
