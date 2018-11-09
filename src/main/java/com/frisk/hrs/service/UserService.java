package com.frisk.hrs.service;

import com.frisk.hrs.pojo.InterviewCustom;
import com.frisk.hrs.pojo.User;
import com.frisk.hrs.pojo.UserCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */
public interface UserService {


    public static final String USER_IS_READ = "用户已读";
    public static final String USER_NOT_READ = "用户未读";
    public static final String USER_IS_INTERVIEW = "同意面试";
    public static final String USER_NOT_INTERVIEW = "同意面试";
    public static final String USER_IS_ENTRY = "同意入职";
    public static final String USER_NOT_ENTRY = "拒绝入职";

    public UserCustom getUserById(Integer id) throws Exception;

    /**
     * 通过用户名和密码获取用户,用于登陆
     *
     * @param username
     * @param password
     * @return
     */
    public UserCustom getUser(String username, String password) throws Exception;

    /**
     * 通过用户名获取用户,用于注册时检查用户名
     *
     * @param username
     * @return
     */
    public User getUserByName(String username) throws Exception;

    /**
     * 根据用户名和密码插入用户,用于注册
     *
     * @param username
     * @param password
     * @return
     */
    public Integer insertUser(String username, String password) throws Exception;

    /**
     * 根据用户名,密码和员工id插入用户,用于已存在员工创建账号
     *
     * @param username
     * @param password
     * @param empId
     * @return
     */
    public Integer insertUser(String username, String password, Integer empId) throws Exception;

    /**
     * 给已存在的账号设置员工id,用于实名认证
     *
     * @param id
     * @param empId
     * @return
     */
    public Integer setEmpId(Integer id, Integer empId) throws Exception;

    /**
     * 设置用户权限
     *
     * @param id
     * @param type
     * @return
     */
    public Integer setType(Integer id, Integer type) throws Exception;

    public List<InterviewCustom> getInterviewWithNotification(Integer uId) throws Exception;

    public Integer updatePassword(Integer uId, String newPassword) throws Exception;

    public Integer attestUser(Integer uId, Integer empId, String empName) throws Exception;
}
