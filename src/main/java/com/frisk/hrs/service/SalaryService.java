package com.frisk.hrs.service;

import com.frisk.hrs.pojo.ObjectionSalaryCustom;
import com.frisk.hrs.pojo.Salary;
import com.frisk.hrs.pojo.SalaryCustom;
import com.frisk.hrs.pojo.SalaryFind;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/15
 */
public interface SalaryService {

    public Salary getSalaryById(Integer id) throws Exception;

    public Salary getSalaryByEmployeeIdAndMonth(Integer id, Date date) throws Exception;

    public Salary computeSalary(Integer empId, Date date, Double performance) throws Exception;

    public Integer objectionSalary(ObjectionSalaryCustom objectionSalaryCustom) throws Exception;

    public Integer processObjectionSalary(Integer salaryId, Integer objId, Double money,Boolean isExecute) throws Exception;

    public List<SalaryCustom> getUnProcessObjectionSalary() throws Exception;
}
