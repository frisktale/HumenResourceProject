package com.frisk.hrs.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class DepartmentFind extends Department implements Serializable {

    private Employee employee;

    public void andNameIs(String name){
        super.setName(name);
    }

    public void andNameLike(String name){
        super.setName("%"+name+"%");
    }

    public void andLeaderIdIs(Integer id){
        employee = new Employee();
        employee.setId(id);
    }
}
