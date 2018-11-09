package com.frisk.hrs.pojo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/12
 */
@Data
public class RewardsFind extends Rewards {

    private String timeInYearMonth;
    private String timeInYearMonthDay;

    private Boolean rewardsOrPunishment;

    public void andIsReward(){
        setRewardsOrPunishment(true);
    }
    public void andIsPunishment(){
        setRewardsOrPunishment(false);
    }

    public void andContentLike(String content){
        setContent("%"+content+"%");
    }

    public void andEmpIdIs(Integer id){
        setEmployeeId(id);
    }

    public void andStartTimeInYearMonthIs(Date date){
        String yearMonth = new SimpleDateFormat("yyyyMM").format(date);
        setTimeInYearMonth(yearMonth);
    }

    public void andStartTimeInYearMonthDayIs(Date date){
        String yearMonthDay = new SimpleDateFormat("yyyyMMdd").format(date);
        setTimeInYearMonthDay(yearMonthDay);
    }
}
