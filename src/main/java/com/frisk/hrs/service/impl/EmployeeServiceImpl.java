package com.frisk.hrs.service.impl;

import com.frisk.hrs.controller.UserController;
import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.mapper.UserMapper;
import com.frisk.hrs.pojo.EmployeeCustom;
import com.frisk.hrs.pojo.EmployeeFind;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.service.EmployeeService;
import com.frisk.hrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier("employeeMapper")
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public EmployeeCustom getEmpById(Integer id) throws Exception {
        EmployeeCustom employee = employeeMapper.findEMpById(id);
        return employee;
    }


    @Override
    public Integer updateEmp(EmployeeCustom employeeCustom) throws Exception {
        Integer line = employeeMapper.updateEmployee(employeeCustom);
        return line;
    }

    @Override
    public Integer insertEmp(EmployeeCustom employeeCustom) throws Exception {
        Integer line = employeeMapper.insertEmployee(employeeCustom);
        return line;
    }

    @Override
    public Integer deleteEmployee(List<Integer> ids) throws Exception {
        for (Integer id : ids) {
            EmployeeCustom eMpById = employeeMapper.findEMpById(id);
            if (eMpById!=null&&eMpById.getUser()!=null) {
                Integer uId = eMpById.getUser().getId();
                userService.setType(uId, UserController.USER);
            }
        }
        Integer row = employeeMapper.deleteList(ids);
        return row;
    }

    @Override
    public Integer recoverDeleteEmployee(List<Integer> ids) throws Exception {
        Integer row = employeeMapper.recoverEmployee(ids);
        return row;
    }

    @Override
    public List<EmployeeCustom> showDeleteEmployee() throws Exception {
        List<EmployeeCustom> employeeCustoms = employeeMapper.showDelete();
        return employeeCustoms;
    }

    @Override
    public Integer cleanDeleteEmployee() throws Exception {
        Integer row = employeeMapper.cleanEmployee();
        return row;
    }

    @Override
    public List<EmployeeCustom> getEmpByPosId(Integer id) throws Exception {
        EmployeeFind employeeFind = new EmployeeFind();
        employeeFind.andPosIdIs(id);
        List<EmployeeCustom> empList = employeeMapper.findEmployeeByCustomer(employeeFind);
        return empList;
    }

    @Override
    public List<EmployeeCustom> getEmpByDepId(Integer id) throws Exception {
        EmployeeFind employeeFind = new EmployeeFind();
        employeeFind.andDepIdIs(id);
        List<EmployeeCustom> empList = employeeMapper.findEmployeeByCustomer(employeeFind);
        return empList;
    }

    @Override
    public List<EmployeeCustom> getAllEmp() throws Exception {
        List<EmployeeCustom> employeeByCustomer = employeeMapper.findEmployeeByCustomer(new EmployeeFind());
        return employeeByCustomer;
    }

    @Override
    public EmployeeCustom getEmpByUserId(Integer id) throws Exception {
        UserCustom userById = userMapper.findUserById(id);
        EmployeeCustom emp = employeeMapper.findEMpById(userById.getEmployee().getId());
        return emp;
    }

    @Override
    public EmployeeCustom getEmpBySalaryId(Integer id) throws Exception {
        EmployeeCustom empBySalaryId = employeeMapper.findEmpBySalaryId(id);
        return empBySalaryId;
    }
}
