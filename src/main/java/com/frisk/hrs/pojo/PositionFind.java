package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class PositionFind extends Position {
    private Integer departmentId;

    public void andNameIs(String name){
        setName(name);
    }

    public void andNameLike(String name){
        setName("%"+name+"%");
    }

    public void andDepartmentId(Integer id){
        setDepartmentId(id);
    }
}
