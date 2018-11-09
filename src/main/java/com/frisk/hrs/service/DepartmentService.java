package com.frisk.hrs.service;

import com.frisk.hrs.pojo.DepartmentCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */

public interface DepartmentService {
    public List<DepartmentCustom> getAllDepartment() throws Exception;

    public Integer addDepartment(DepartmentCustom departmentCustom) throws Exception;

    public Integer updateDepartment(DepartmentCustom departmentCustom) throws Exception;

    public Integer deleteDepartment(List<Integer> ids) throws Exception;

    public Integer recoverDeleteDepartment(List<Integer> ids) throws Exception;

    public List<DepartmentCustom> showDeleteDepartment() throws Exception;

    public Integer cleanDeleteDepartment() throws Exception;

    public DepartmentCustom getDepartmentById(Integer integer) throws Exception;
}
