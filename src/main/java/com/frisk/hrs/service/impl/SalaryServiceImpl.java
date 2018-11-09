package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.AttendanceMapper;
import com.frisk.hrs.mapper.ObjectionSalaryMapper;
import com.frisk.hrs.mapper.RewardsMapper;
import com.frisk.hrs.mapper.SalaryMapper;
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
 * @date 2018/10/15
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    @Qualifier("salaryMapper")
    private SalaryMapper salaryMapper;

    @Autowired
    private RewardsMapper rewardsMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private ObjectionSalaryMapper objectionSalaryMapper;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Salary getSalaryById(Integer id) throws Exception {
        Salary salary = salaryMapper.findSalaryByIdBase(id);
        return salary;
    }

    @Override
    public Salary getSalaryByEmployeeIdAndMonth(Integer id, Date date) throws Exception {
        SalaryFind salaryFind = new SalaryFind();
        salaryFind.andEmployeeIdIs(id);
        salaryFind.andTimeInYearMonth(date);
        List<Salary> salary = salaryMapper.findSalaryByCustom(salaryFind);
        if (salary.size() == 0) {
            return null;
        } else {
            return salary.get(0);
        }
    }


    @Override
    public Salary computeSalary(Integer empId, Date date, Double performance) throws Exception {
        SalaryFind salaryFind = new SalaryFind();
        salaryFind.andEmployeeIdIs(empId);
        salaryFind.andTimeInYearMonth(date);
        List<Salary> salaryByCustom = salaryMapper.findSalaryByCustom(salaryFind);

        if (salaryByCustom.size() == 0) {
            AttendanceFind attendanceFind = new AttendanceFind();
            attendanceFind.andOffTimeInYearMonthIs(date);
            attendanceFind.andEmployeeIdIs(empId);
            List<Attendance> attendance = attendanceMapper.findAttendanceByCustom(attendanceFind);

            double dailySalary = employeeService.getEmpById(empId).getEmployee().getSalary() / AttendanceService.WORK_DAYS;
            int i = AttendanceService.WORK_DAYS - attendance.size();
            if (i > 0) {
                rewardsMapper.insertRewards(new Rewards(0, date, "旷工" + i + "天", empId, false, -i * dailySalary));
            }

            RewardsFind rewardsFind = new RewardsFind();
            rewardsFind.andStartTimeInYearMonthIs(date);
            rewardsFind.andEmpIdIs(empId);
            rewardsFind.andContentLike("加班");
            Double overTime = rewardsMapper.getTotalMoneyByCustom(rewardsFind);
            RewardsFind rewardsFind1 = new RewardsFind();
            rewardsFind1.andStartTimeInYearMonthIs(date);
            rewardsFind1.andEmpIdIs(empId);
            Double rewards = rewardsMapper.getTotalMoneyByCustom(rewardsFind1) - overTime;

            Salary salary = new Salary();
            salary.setBasic(employeeService.getEmpById(empId).getEmployee().getSalary());
            salary.setEmployeeId(empId);
            salary.setTime(date);
            salary.setOvertime(overTime);
            salary.setRewards(rewards);
            salary.setPerformance(performance);
            Integer line = salaryMapper.insertSalary(salary);


            return salary;
        }
        return null;
    }

    @Override
    public Integer objectionSalary(ObjectionSalaryCustom objectionSalaryCustom) throws Exception {
        ObjectionSalaryCustom objectionSalaryBySalaryId = objectionSalaryMapper.findObjectionSalaryBySalaryId(objectionSalaryCustom.getSalary().getId());
        if (objectionSalaryBySalaryId == null) {
            Integer line = objectionSalaryMapper.insertObjectionSalary(objectionSalaryCustom);
            Salary salary = new Salary();
            salary.setId(objectionSalaryCustom.getSalary().getId());
            salary.setIsDisagree(true);
            salary.setIsProcess(false);
            salaryMapper.updateSalary(salary);
            return line;
        }else if (objectionSalaryBySalaryId.getObjectionSalary().getIsExecute()!=true){
            objectionSalaryBySalaryId.getObjectionSalary().setMoney(objectionSalaryCustom.getObjectionSalary().getMoney());
            objectionSalaryBySalaryId.getObjectionSalary().setContent(objectionSalaryCustom.getObjectionSalary().getContent());
            objectionSalaryMapper.updateObjectionSalary(objectionSalaryBySalaryId);
            Salary salary = new Salary();
            salary.setId(objectionSalaryCustom.getSalary().getId());
            salary.setIsDisagree(true);
            salary.setIsProcess(false);
            Integer line = salaryMapper.updateSalary(salary);
            return line;
        }else {
            return 0;
        }
    }

    @Override
    public Integer processObjectionSalary(Integer salaryId, Integer objId, Double money, Boolean isExecute) throws Exception {
        ObjectionSalaryCustom objectionSalaryBySalaryId = objectionSalaryMapper.findObjectionSalaryBySalaryId(salaryId);
        if (objectionSalaryBySalaryId != null) {
            ObjectionSalaryCustom objectionSalaryCustom = new ObjectionSalaryCustom();
            ObjectionSalary objectionSalary = new ObjectionSalary();
            objectionSalary.setId(objId);
            objectionSalary.setIsExecute(isExecute);
            objectionSalary.setReissueTime(new Date());
            objectionSalaryCustom.setObjectionSalary(objectionSalary);
            objectionSalaryMapper.updateObjectionSalary(objectionSalaryCustom);

            if (isExecute) {
                Date time = objectionSalaryBySalaryId.getSalary().getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                Rewards rewards = new Rewards(0, calendar.getTime(),
                        "补发",
                        objectionSalaryBySalaryId.getSalary().getEmployeeId(), null,
                        money);
                rewardsMapper.insertRewards(rewards);
            }
            Salary salary = new Salary();
            salary.setId(salaryId);
            salary.setIsDisagree(true);
            salary.setIsProcess(true);
            salaryMapper.updateSalary(salary);
            return 1;
        }
        return 0;
    }

    @Override
    public List<SalaryCustom> getUnProcessObjectionSalary() throws Exception {
        SalaryFind salaryFind = new SalaryFind();
        salaryFind.andIsProcess(false);
        salaryFind.andIsDisagree(true);
        List<SalaryCustom> unProcessSalary = salaryMapper.findSalaryCustomByCustom(salaryFind);
        return unProcessSalary;
    }
}
