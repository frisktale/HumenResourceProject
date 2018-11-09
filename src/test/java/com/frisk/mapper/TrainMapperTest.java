package com.frisk.mapper;

import com.frisk.hrs.mapper.TrainMapper;
import com.frisk.hrs.pojo.TrainFind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * @author frisktale
 * @date 2018/10/9
 */

@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class TrainMapperTest {


    @Autowired
    private TrainMapper trainMapper;

    @Test
    public void test1(){
        try {
            System.out.println(trainMapper.findTrainById(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*TrainFind trainFind = new TrainFind();
        trainFind.andContentLike("培训");
        System.out.println(trainMapper.findTrainByCustomer(trainFind));*/
    }

}