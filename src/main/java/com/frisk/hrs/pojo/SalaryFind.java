package com.frisk.hrs.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalaryFind extends Salary {

    private String  timeYear;
    private String  timeYearMonth;

    public void andTimeInYear(Date year){
        String year1 = new SimpleDateFormat("yyyy").format(year);
        setTimeYear(year1);
    }

    public void andTimeInYearMonth(Date month){
        String yearMonth = new SimpleDateFormat("yyyyMM").format(month);
        setTimeYearMonth(yearMonth);
    }

    public void andEmployeeIdIs(Integer id){
        setEmployeeId(id);
    }

    public void andIsDisagree(Boolean disagree){
        setIsDisagree(disagree);
    }

    public void andIsProcess(Boolean process){
        setIsProcess(process);
    }
}
