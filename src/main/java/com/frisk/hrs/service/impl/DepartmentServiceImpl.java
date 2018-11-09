package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.DepartmentMapper;
import com.frisk.hrs.pojo.Department;
import com.frisk.hrs.pojo.DepartmentCustom;
import com.frisk.hrs.pojo.DepartmentFind;
import com.frisk.hrs.pojo.Employee;
import com.frisk.hrs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentCustom> getAllDepartment() throws Exception {
        DepartmentFind departmentFind = new DepartmentFind();
        List<DepartmentCustom> departments = departmentMapper.findDepartmentByCustomer(departmentFind);
        return departments;
    }

    @Override
    public Integer addDepartment(DepartmentCustom departmentCustom) throws Exception {
        String name = departmentCustom.getDepartment().getName();
        DepartmentFind departmentFind = new DepartmentFind();
        departmentFind.andNameIs(name);
        List<DepartmentCustom> departmentByCustomer = departmentMapper.findDepartmentByCustomer(departmentFind);
        if (departmentByCustomer.size()>0){
            return 0;
        }
        departmentCustom.getDepartment().setCreateTime(new Date());
        Integer line = departmentMapper.insertDepartment(departmentCustom);
        return line;
    }

    @Override
    public Integer updateDepartment(DepartmentCustom departmentCustom) throws Exception {
        String name = departmentCustom.getDepartment().getName();
        DepartmentFind departmentFind = new DepartmentFind();
        departmentFind.andNameIs(name);
        List<DepartmentCustom> departmentByCustomer = departmentMapper.findDepartmentByCustomer(departmentFind);
        if (departmentByCustomer.size()>0){
            return 0;
        }
        Integer row = departmentMapper.updateDepartment(departmentCustom);
        return row;
    }

    @Override
    public Integer deleteDepartment(List<Integer> ids) throws Exception {
        for (Integer id : ids) {
            List<Employee> employees = departmentMapper.findDepartmentById(id).getEmployees();
            if (employees.size()!=0){
                return 0;
            }
        }

        Integer row = departmentMapper.deleteList(ids);
        return row;
    }

    @Override
    public Integer recoverDeleteDepartment(List<Integer> ids) throws Exception {
        Integer row = departmentMapper.recoverDepartment(ids);
        return row;
    }

    @Override
    public List<DepartmentCustom> showDeleteDepartment() throws Exception {
        List<DepartmentCustom> departmentCustoms = departmentMapper.showDelete();
        return departmentCustoms;
    }

    @Override
    public Integer cleanDeleteDepartment() throws Exception {
        Integer row = departmentMapper.cleanDepartment();
        return row;
    }

    @Override
    public DepartmentCustom getDepartmentById(Integer id) throws Exception {
        DepartmentCustom department = departmentMapper.findDepartmentById(id);
        return department;
    }
}
