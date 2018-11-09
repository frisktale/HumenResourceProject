package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class TrainFind extends Train {

    private Integer departmentId;

    private Integer positionId;

    public void andContentLike(String content){
        setContent("%"+content+"%");
    }

    public void andDepartmentIdIs(Integer id){
        setDepartmentId(id);
    }
    public void andPositionIdIdIs(Integer id){
        setPositionId(id);
    }
}
