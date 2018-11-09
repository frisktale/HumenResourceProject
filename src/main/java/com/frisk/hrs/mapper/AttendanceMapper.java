package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Attendance;
import com.frisk.hrs.pojo.AttendanceFind;

import java.util.List;

public interface AttendanceMapper {
    public Attendance findAttendanceByIdBase(Integer id) throws Exception;

    public List<Attendance> findAttendanceByCustom(AttendanceFind attendanceFind) throws Exception;

    public Integer insertAttendance(Attendance AttendanceCustom)throws Exception;

    public Integer updateAttendance(Attendance AttendanceCustom)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<Attendance> showDelete()throws Exception;

    public Integer recoverAttendance(List<Integer> ids)throws Exception;

    public Integer cleanAttendance()throws Exception;

}