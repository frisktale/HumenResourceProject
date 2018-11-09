package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Department;
import com.frisk.hrs.pojo.DepartmentCustom;
import com.frisk.hrs.pojo.DepartmentFind;

import java.util.List;

/**
 * @author frisktale
 */
public interface DepartmentMapper {
    public DepartmentCustom findDepartmentById(Integer id) throws Exception;
    public Department findDepartmentByIdBase(Integer id) throws Exception;

    public List<DepartmentCustom> findDepartmentByCustomer(DepartmentFind departmentFind) throws Exception;

    public Integer insertDepartment(DepartmentCustom departmentCustom) throws Exception;

    public Integer updateDepartment(DepartmentCustom departmentCustom) throws Exception;

    public Integer deleteList(List<Integer> ids) throws Exception;

    public List<DepartmentCustom> showDelete()throws Exception;

    public Integer recoverDepartment(List<Integer> ids)throws Exception;

    public Integer cleanDepartment()throws Exception;
}