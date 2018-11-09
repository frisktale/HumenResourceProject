package com.frisk.service;

import com.frisk.hrs.mapper.UserMapper;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.pojo.UserFind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class UserServiceTest {
    @Autowired
    UserMapper userMapper = null;

    @Test
    public void test1(){
        UserFind userFind = new UserFind();
        /*userFind.setUsername("py");
        userFind.setPassword("py");*/
        try {
            List<UserCustom> userList = userMapper.findUserByUserCustomer(userFind);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
