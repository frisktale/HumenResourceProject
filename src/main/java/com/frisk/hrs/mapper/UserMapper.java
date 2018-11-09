package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.User;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.pojo.UserFind;

import java.util.List;

public interface UserMapper {
    public UserCustom findUserById(Integer id)throws Exception;

    public User findUserByIdBase(Integer id)throws Exception;
    public User findUserByEmpIdBase(Integer id)throws Exception;

    public List<UserCustom> findUserByUserCustomer(UserFind userFind)throws Exception;

    public Integer insertUser(UserCustom user)throws Exception;

    public Integer updateUserNotSetNull(UserCustom user)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<UserCustom> showDelete()throws Exception;

    public Integer recoverUser(List<Integer> ids)throws Exception;

    public Integer cleanUser()throws Exception;
}