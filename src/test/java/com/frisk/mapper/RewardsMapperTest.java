package com.frisk.mapper;

import com.frisk.hrs.mapper.RewardsMapper;
import com.frisk.hrs.pojo.RewardsFind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/13
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class RewardsMapperTest {
    @Autowired
    private RewardsMapper rewardsMapper;

    @Test
    public void test1() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-13");
        RewardsFind rewardsFind = new RewardsFind();
        rewardsFind.andIsPunishment();
        rewardsFind.andStartTimeInYearMonthIs(date);
        rewardsFind.andEmpIdIs(2);
        System.out.println(rewardsMapper.findRewardsByCustom(rewardsFind));
    }

}
