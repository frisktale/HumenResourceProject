package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Salary;
import com.frisk.hrs.pojo.SalaryCustom;
import com.frisk.hrs.pojo.SalaryFind;

import java.util.List;

public interface SalaryMapper {
    public Salary findSalaryByIdBase(Integer id) throws Exception;

    public SalaryCustom findSalaryById(Integer id) throws Exception;
    public List<Salary> findSalaryByCustom(SalaryFind salaryFind) throws Exception;
    public List<SalaryCustom> findSalaryCustomByCustom(SalaryFind salaryFind) throws Exception;

    public Integer insertSalary(Salary salary)throws Exception;

    public Integer updateSalary(Salary salary)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<Salary> showDelete()throws Exception;

    public Integer recoverSalary(List<Integer> ids)throws Exception;

    public Integer cleanSalary()throws Exception;
}