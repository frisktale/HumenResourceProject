package com.frisk.hrs.service;

import com.frisk.hrs.pojo.Attendance;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/12
 */
public interface AttendanceService {

    public static final Integer WORK_DAYS = 20;
    public static final Integer START_WORK_HOUR = 9;
    public static final Integer OFF_WORK_HOUR = 17;

    public Attendance findAttendanceById(Integer id) throws Exception;

    public Attendance findAttendanceByDayAndEmployeeId(Date date,Integer eId) throws Exception;

    public List<Attendance> findAttendanceByMonthAndEmployeeId(Date date, Integer eId) throws Exception;

    public Integer startCheck(Date date,Integer eId) throws Exception;
    public Integer offCheck(Date date,Integer eId) throws Exception;

}
