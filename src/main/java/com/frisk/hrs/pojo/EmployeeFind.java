package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class EmployeeFind extends Employee {
    private Integer departmentId;

    private Integer positionId;

    public void andEmpNameIs(String name){
        super.setName(name);
    }

    public void andEmpNameLike(String name){
        super.setName("%"+name+"%");
    }

    public void andDepIdIs(Integer id){
        setDepartmentId(id);
    }

    public void andPosIdIs(Integer id){
        setPositionId(id);
    }

    public void andStatusIs(String status){
        setStatus(status);
    }
}
