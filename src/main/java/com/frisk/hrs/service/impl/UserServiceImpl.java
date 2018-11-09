package com.frisk.hrs.service.impl;

import com.frisk.hrs.controller.UserController;
import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.mapper.InterviewMapper;
import com.frisk.hrs.mapper.UserMapper;
import com.frisk.hrs.pojo.*;
import com.frisk.hrs.service.InterviewService;
import com.frisk.hrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    @Qualifier("interviewMapper")
    private InterviewMapper interviewMapper;

    @Override
    public UserCustom getUserById(Integer id) throws Exception {
        UserCustom user = userMapper.findUserById(id);
        return user;
    }

    @Override
    public UserCustom getUser(String username, String password) throws Exception {
        UserFind userFind = new UserFind();
        userFind.setUsername(username);
        userFind.setPassword(password);
        List<UserCustom> userList = userMapper.findUserByUserCustomer(userFind);
        if (userList.size() != 1) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User getUserByName(String username) throws Exception {
        UserFind userFind = new UserFind();
        userFind.setUsername(username);
        List<UserCustom> userList = userMapper.findUserByUserCustomer(userFind);
        if (userList.size() != 1) {
            return null;
        }
        return userList.get(0).getUser();
    }

    @Override
    public Integer insertUser(String username, String password) throws Exception {
        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(UserController.USER);
        userCustom.setUser(user);
        Integer line = userMapper.insertUser(userCustom);
        return line;
    }

    @Override
    public Integer insertUser(String username, String password, Integer empId) throws Exception {
        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userCustom.setUser(user);
        Employee employee = new Employee();
        employee.setId(empId);
        userCustom.setEmployee(employee);
        Integer line = userMapper.insertUser(userCustom);
        return line;
    }

    @Override
    public Integer setEmpId(Integer id, Integer empId) throws Exception {

        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setId(id);
        userCustom.setUser(user);
        Employee employee = new Employee();
        employee.setId(empId);
        userCustom.setEmployee(employee);
        Integer line = userMapper.updateUserNotSetNull(userCustom);
        return line;
    }

    @Override
    public Integer setType(Integer id, Integer type) throws Exception {
        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setId(id);
        user.setType(type);
        userCustom.setUser(user);
        Integer line = userMapper.updateUserNotSetNull(userCustom);
        return line;
    }

    @Override
    public List<InterviewCustom> getInterviewWithNotification(Integer uId) throws Exception {
        InterviewFind interviewFind = new InterviewFind();
        interviewFind.andUserIdIs(uId);
        interviewFind.andUserStatusIs(UserService.USER_NOT_READ);
        List<InterviewCustom> interview = interviewMapper.findInterviewByCustomer(interviewFind);
        return interview;
    }

    @Override
    public Integer updatePassword(Integer uId, String newPassword) throws Exception {
        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setId(uId);
        user.setPassword(newPassword);
        userCustom.setUser(user);
        Integer row = userMapper.updateUserNotSetNull(userCustom);
        return row;
    }

    @Override
    public Integer attestUser(Integer uId, Integer empId, String empName) throws Exception {


        EmployeeCustom eMpById = employeeMapper.findEMpById(empId);
        if (!empName.equals(eMpById.getEmployee().getName())) {
            return 0;
        }

        UserFind userFind = new UserFind();
        userFind.andEmployeeIdIs(empId);
        List<UserCustom> userByUserCustomer = userMapper.findUserByUserCustomer(userFind);
        if (userByUserCustomer.size() != 0) {
            return 0;
        }
        UserCustom userCustom = new UserCustom();
        User user = new User();
        user.setType(UserController.EMPLOYEE);
        user.setId(uId);
        Employee employee = new Employee();
        employee.setId(empId);
        userCustom.setUser(user);
        userCustom.setEmployee(employee);
        Integer row = userMapper.updateUserNotSetNull(userCustom);
        return row;
    }
}
