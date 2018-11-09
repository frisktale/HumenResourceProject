package com.frisk.mapper;

import com.frisk.hrs.mapper.ObjectionSalaryMapper;
import com.frisk.hrs.pojo.ObjectionSalary;
import com.frisk.hrs.pojo.ObjectionSalaryCustom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author frisktale
 * @date 2018/10/16
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class SalaryMapperTest {
    @Autowired
    private ObjectionSalaryMapper objectionSalaryMapper;

    @Test
    public void test1(){
        ObjectionSalaryCustom objectionSalaryCustom = new ObjectionSalaryCustom();
        objectionSalaryMapper.insertObjectionSalary(objectionSalaryCustom);
    }
}
