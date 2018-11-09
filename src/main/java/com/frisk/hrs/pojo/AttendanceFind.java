package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class AttendanceFind extends Attendance {

    private String startTimeInYearMonth;
    private String offTimeInYearMonth;
    private String startTimeInYearMonthDay;

    public void andStatusIs(String status) {
        setStatus(status);
    }

    public void andEmployeeIdIs(Integer id){
        setEmployeeId(id);
    }

    public void andStartTimeInYearMonthIs(Date date){
        String yearMonth = new SimpleDateFormat("yyyyMM").format(date);
        setStartTimeInYearMonth(yearMonth);
    }
    public void andOffTimeInYearMonthIs(Date date){
        String yearMonth = new SimpleDateFormat("yyyyMM").format(date);
        setOffTimeInYearMonth(yearMonth);
    }

    public void andStartTimeInYearMonthDayIs(Date date){
        String yearMonthDay = new SimpleDateFormat("yyyyMMdd").format(date);
        setStartTimeInYearMonthDay(yearMonthDay);
    }
}