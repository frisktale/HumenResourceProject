package com.frisk.mapper;

import com.frisk.hrs.mapper.InterviewMapper;
import com.frisk.hrs.pojo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class InterviewMapperTest {

    @Autowired
    private InterviewMapper interviewMapper;


    @Test
    public void test1() throws Exception {
        InterviewCustom interviewCustom = new InterviewCustom();
        interviewCustom.setInterview(new Interview(0,new Date(),new Date(),null,null,false));
        interviewCustom.setEmployee(new Employee());
        interviewCustom.setOffers(new Offers());
        interviewCustom.setResume(new Resume());
        interviewCustom.setUser(new User());
        interviewMapper.insertInterview(interviewCustom);
    }
}
