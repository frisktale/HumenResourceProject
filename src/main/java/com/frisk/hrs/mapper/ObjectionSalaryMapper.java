package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.ObjectionSalary;
import com.frisk.hrs.pojo.ObjectionSalaryCustom;

import java.util.List;

public interface ObjectionSalaryMapper {

    public List<ObjectionSalaryCustom> getUnProcessObjectionSalary() throws Exception;

    public ObjectionSalary findObjectionSalaryByIdBase(Integer id);

    public ObjectionSalaryCustom findObjectionSalaryById(Integer id);

    public ObjectionSalaryCustom findObjectionSalaryBySalaryId(Integer id);

    public Integer insertObjectionSalary(ObjectionSalaryCustom objectionSalaryCustom);

    public Integer updateObjectionSalary(ObjectionSalaryCustom objectionSalaryCustom);

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<ObjectionSalaryCustom> showDelete()throws Exception;

    public Integer recoverObjectionSalary(List<Integer> ids)throws Exception;

    public Integer cleanObjectionSalary()throws Exception;

}