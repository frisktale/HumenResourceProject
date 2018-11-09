package com.frisk;

import com.frisk.hrs.mapper.UserMapper;
import com.frisk.hrs.pojo.UserCustom;
import com.frisk.hrs.pojo.UserFind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */
public class MyTest {

    ApplicationContext applicationContext = null;

    @BeforeEach
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-dao-config.xml");

    }

    @Test
    public void test1(){
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        UserFind userFind = new UserFind();
        userFind.setUsername("py");
        userFind.setPassword("py");
        List<UserCustom> userByUserFind = null;
        try {
            userByUserFind = userMapper.findUserByUserCustomer(userFind);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userByUserFind.get(0).getResume());
    }

    @Test
    public void test2(){
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        UserCustom userCustom = new UserCustom();

        try {
            System.out.println(userMapper.insertUser(userCustom));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        try {
            System.out.println(userMapper.recoverUser(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));*/
        Double aDouble = new Double(null);
        double v = aDouble - 300;
        System.out.println(v);
    }

    @Test
    public void test5(){
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.getTime());
    }
}
