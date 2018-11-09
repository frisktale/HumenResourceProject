package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.AttendanceMapper;
import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.mapper.RewardsMapper;
import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.AttendanceService;
import com.frisk.hrs.service.EmployeeService;
import com.frisk.hrs.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/12
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {


    @Autowired
    @Qualifier("attendanceMapper")
    private AttendanceMapper attendanceMapper;


    @Autowired
    @Qualifier("employeeMapper")
    private EmployeeMapper employeeMapper;

    @Autowired
    @Qualifier("rewardsMapper")
    private RewardsMapper rewardsMapper;

    @Override
    public Attendance findAttendanceById(Integer id) throws Exception {
        Attendance attendance = attendanceMapper.findAttendanceByIdBase(id);
        return attendance;
    }

    @Override
    public Attendance findAttendanceByDayAndEmployeeId(Date date, Integer eId) throws Exception {
        AttendanceFind attendanceFind = new AttendanceFind();
        attendanceFind.andEmployeeIdIs(eId);
        attendanceFind.andStartTimeInYearMonthDayIs(date);
        List<Attendance> attendanceList = attendanceMapper.findAttendanceByCustom(attendanceFind);
        if (attendanceList.size() == 0) {
            return null;
        } else {
            return attendanceList.get(0);
        }
    }

    @Override
    public List<Attendance> findAttendanceByMonthAndEmployeeId(Date date, Integer eId) throws Exception {
        AttendanceFind attendanceFind = new AttendanceFind();
        attendanceFind.andEmployeeIdIs(eId);
        attendanceFind.andStartTimeInYearMonthIs(date);
        List<Attendance> attendanceList = attendanceMapper.findAttendanceByCustom(attendanceFind);
        return attendanceList;
    }

    @Override
    public Integer startCheck(Date date, Integer eId) throws Exception {

        Attendance attendance = this.findAttendanceByDayAndEmployeeId(date, eId);
        if (attendance == null) {
            Attendance attendance1 = new Attendance();
            attendance1.setEmployeeId(eId);
            attendance1.setStartWorkTime(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int i = calendar.get(Calendar.DAY_OF_WEEK);


            Boolean isLate = calendar.get(Calendar.HOUR_OF_DAY) > AttendanceService.START_WORK_HOUR ||
                    (calendar.get(Calendar.HOUR_OF_DAY) == AttendanceService.START_WORK_HOUR &&
                            calendar.get(Calendar.MINUTE) >= 1);


            attendance1.setIsLate(isLate);
            int i1 = calendar.get(Calendar.HOUR_OF_DAY) - AttendanceService.START_WORK_HOUR;
            if (i1 >= 3) {
                attendance1.setIsAbsent(true);
                attendance1.setStatus("旷工");
                attendance1.setOffWorkTime(date);
            }
            Integer line = attendanceMapper.insertAttendance(attendance1);
            return line;
        } else {
            return 0;
        }
    }

    @Override
    public Integer offCheck(Date date, Integer eId) throws Exception {
        Attendance attendance = this.findAttendanceByDayAndEmployeeId(date, eId);
        System.out.println(attendance);
        Attendance attendance1 = new Attendance();
        attendance1.setId(attendance.getId());
        attendance1.setOffWorkTime(date);
        if (attendance != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Integer i = calendar.get(Calendar.HOUR_OF_DAY) - AttendanceService.OFF_WORK_HOUR;
            Double dailySalary = employeeMapper.findEMpById(eId).getEmployee().getSalary() / AttendanceService.WORK_DAYS;

            if (i > 0) {
                attendance1.setStatus("加班");

                if (attendance.getIsLate()&&(attendance.getIsAbsent()==null||!attendance.getIsAbsent())) {
                    rewardsMapper.insertRewards(new Rewards(0, date, "迟到", eId, null, -50d));
                }
                System.out.println(attendance1);
                rewardsMapper.insertRewards(new Rewards(0, date, "加班", eId, null, dailySalary / 4 * i));
            }
            if (i < 0 && i > -3) {
                attendance1.setStatus("早退");
                if (attendance.getIsLate()) {
                    rewardsMapper.insertRewards(new Rewards(0, date, "迟到", eId, null, -50d));
                }
                rewardsMapper.insertRewards(new Rewards(0, date, "早退", eId, null, -dailySalary / 4 * i));
            }
            if (i <= -3||(attendance.getIsAbsent()!=null&&attendance.getIsAbsent())) {
                attendance1.setIsAbsent(true);
                attendance1.setStatus("旷工");
                rewardsMapper.insertRewards(new Rewards(0, date, "旷工", eId, null, -dailySalary));
            }
            Integer line = attendanceMapper.updateAttendance(attendance1);
            return line;
        }
        return 0;
    }

    /*@Override
    public void absentCheck(Date date) throws Exception {
        System.out.println(date);
        AttendanceFind attendanceFind = new AttendanceFind();
        attendanceFind.andStartTimeInYearMonthDayIs(date);
        List<EmployeeCustom> employees = employeeMapper.findEmployeeByCustomer(new EmployeeFind());
        for (int i = 0; i < employees.size(); i++) {
            attendanceFind.setEmployeeId(employees.get(i).getEmployee().getId());
            List<Attendance> attendance = attendanceMapper.findAttendanceByCustom(attendanceFind);
//            Attendance attendance = attendanceMapper.findAttendanceByEmployeeIdBase(employees.get(i).getEmployee().getId());
            if (attendance.size() == 0) {
                Attendance attendance1 = new Attendance();
                attendance1.setEmployeeId(employees.get(i).getEmployee().getId());
                attendance1.setStartWorkTime(date);
                attendance1.setIsAbsent(true);
                attendanceMapper.insertAttendance(attendance1);
            } else if (attendance.get(0).getOffWorkTime() == null) {
                attendance.get(0).setIsAbsent(true);
                attendanceMapper.updateAttendance(attendance.get(0));
            }
        }
    }*/
}
