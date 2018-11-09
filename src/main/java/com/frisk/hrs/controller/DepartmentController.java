package com.frisk.hrs.controller;

import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.DepartmentService;
import com.frisk.hrs.service.EmployeeService;
import com.frisk.hrs.service.PositionService;
import com.frisk.hrs.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Controller
@RequestMapping("dep")
public class DepartmentController {

   /* $("#showEmpByDep").click(function () {
        alert("showEmpByDep"+data.value);
    });
    $("#showTrainByDep").click(function () {
        alert("showTrainByDep"+data.value);
    });
});
        form.on('select(position)', function (data) {

        $("#showEmpByPos").click(function () {
        alert("showEmpByPos"+data.value);
        });
        $("#showTrainByPos").click(function () {
        alert("showTrainByPos"+data.value);
        });*/



    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;
    @Autowired
    private EmployeeService employeeService;


    @RequestMapping("showDepartment")
    public ModelAndView showDepartment() {
        ModelAndView mav = new ModelAndView("department");
        List<DepartmentCustom> allDepartment = null;
        try {
            allDepartment = departmentService.getAllDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("department", allDepartment);
        return mav;
    }


    @RequestMapping("getPosition")
    @ResponseBody
    public Position getPositionById(Integer id) {
        Position position = null;
        try {
            position = positionService.getPositionById(id).getPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    @ResponseBody
    @RequestMapping("getDepartment")
    public Map<String, Object> getDepartment(Integer id) {
        Map<String, Object> dep = new HashMap<>();
        DepartmentCustom department = null;
        try {
            department = departmentService.getDepartmentById(id);
            dep.put("depName", department.getDepartment().getName());
            dep.put("depId", department.getDepartment().getId());
            dep.put("leader", department.getLeader());
            dep.put("employee", department.getEmployees());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dep;
    }

    @RequestMapping("getPositions")
    @ResponseBody
    public List<Position> getPositionByDepId2(Integer id) {
        ArrayList<Position> positions = new ArrayList<>();
        DepartmentCustom department = null;
        try {
            department = departmentService.getDepartmentById(id);
            positions.addAll(department.getPositions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }





}
