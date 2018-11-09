package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.EmployeeCustom;
import com.frisk.hrs.pojo.EmployeeFind;
import com.frisk.hrs.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {
    public Employee findEmpByIdBase(Integer id)throws Exception;

    public EmployeeCustom findEMpById(Integer id)throws Exception;

    public List<EmployeeCustom> findEmployeeByCustomer(EmployeeFind employeeFind)throws Exception;

    public Integer insertEmployee(EmployeeCustom employeeCustom)throws Exception;

    public Integer updateEmployee(EmployeeCustom employeeCustom)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<EmployeeCustom> showDelete()throws Exception;

    public Integer recoverEmployee(List<Integer> ids)throws Exception;

    public Integer cleanEmployee()throws Exception;

    public List<Employee> findEmpByTrainId(Integer id)throws Exception;

    public EmployeeCustom findEmpBySalaryId(Integer id) throws Exception;

}