package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@Data
public class OffersFind extends Offers {

    private Integer departmentId;

    private Integer positionId;

    private Boolean notHappened;

    public void andMessageLike(String message) {
        setMessage("%" + message + "%");
    }

    public void andNumberIs(Long number) {
        setNumber(number);
    }

    public void andDepIdIs(Integer id) {
        setDepartmentId(id);
    }

    public void andPosIdIs(Integer id) {
        setPositionId(id);
    }

    public void andNotHappened(){
        setNotHappened(true);
    }
}
