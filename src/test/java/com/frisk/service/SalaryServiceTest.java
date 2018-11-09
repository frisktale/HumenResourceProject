package com.frisk.service;

import com.frisk.hrs.service.SalaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/16
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class SalaryServiceTest {
    @Autowired
    private SalaryService salaryService;

    @Test
    public void test1() throws Exception {
        System.out.println(salaryService.computeSalary(2, new Date(), 10000d));
    }
}
