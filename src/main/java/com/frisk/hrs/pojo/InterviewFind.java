package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/10
 * <p>
 * private Boolean isRead;
 * <p>
 * private Date deliveryTime;
 * <p>
 * private Boolean isNotification;
 * <p>
 * private Date interviewTime;
 * <p>
 * private Boolean isInterview;
 * <p>
 * <p>
 * private Boolean isEmploy;
 */
@Data
public class InterviewFind extends Interview {
    private Integer userId;

    private Integer offerId;

    private Integer resumeId;

    private Integer employeeId;

    public void andUserIdIs(Integer id){
        setUserId(id);
    }
    public void andOfferIdIdIs(Integer id){
        setOfferId(id);
    }
    public void andResumeIdIs(Integer id){
        setResumeId(id);
    }
    public void andEmployeeIdIs(Integer id){
        setEmployeeId(id);
    }

    public void andStatusIs(String status){
        setStatus(status);
    }
    public void andUserStatusIs(String userStatus){
        setUserStatus(userStatus);
    }
}
