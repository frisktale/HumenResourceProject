package com.frisk.mapper;

import com.frisk.hrs.mapper.OffersMapper;
import com.frisk.hrs.pojo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class OffersMapperTest {

    @Autowired
    private OffersMapper offersMapper;

    @Test
    public void test1() throws Exception {
//        System.out.println(offersMapper.findOffersById(1));
        OffersFind find = new OffersFind();
        find.andMessageLike("java");
        System.out.println(offersMapper.findOffersByCustomer(find));
    }

    @Test
    public void test2() throws Exception {
        OffersCustom offersCustom = new OffersCustom();
        offersCustom.setOffers(new Offers(0, "学徒,无条件", 20l, new SimpleDateFormat("yyyy-MM-dd").parse("2019-1-1")));
        Department department = new Department();
        department.setId(1);
        offersCustom.setDepartment(department);
        Position position = new Position();
        position.setId(2);
        offersCustom.setPosition(position);
        offersMapper.insertOffers(offersCustom);
    }

    @Test
    public void test3() throws Exception {
        OffersCustom offersCustom = new OffersCustom();
        Offers offers = new Offers();
        offers.setId(1);
        offers.setNumber(7l);
        offersCustom.setOffers(offers);
        offersMapper.updateOffers(offersCustom);
    }
}
