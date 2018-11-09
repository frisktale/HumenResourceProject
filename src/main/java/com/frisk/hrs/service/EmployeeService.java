package com.frisk.hrs.service;

import com.frisk.hrs.pojo.EmployeeCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
public interface EmployeeService {
    public EmployeeCustom getEmpById(Integer id) throws Exception;

    public Integer updateEmp(EmployeeCustom employeeCustom) throws Exception;

    public Integer insertEmp(EmployeeCustom employeeCustom) throws Exception;

    public Integer deleteEmployee(List<Integer> ids) throws Exception;

    public Integer recoverDeleteEmployee(List<Integer> ids) throws Exception;

    public List<EmployeeCustom> showDeleteEmployee() throws Exception;

    public Integer cleanDeleteEmployee() throws Exception;

    public List<EmployeeCustom> getEmpByPosId(Integer id) throws Exception;

    public List<EmployeeCustom> getEmpByDepId(Integer id) throws Exception;
    public List<EmployeeCustom> getAllEmp() throws Exception;

    public EmployeeCustom getEmpByUserId(Integer id) throws Exception;
    public EmployeeCustom getEmpBySalaryId(Integer id) throws Exception;
}
