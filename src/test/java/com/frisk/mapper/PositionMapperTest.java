package com.frisk.mapper;

import com.frisk.hrs.mapper.PositionMapper;
import com.frisk.hrs.pojo.PositionFind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

/**
 * @author frisktale
 * @date 2018/10/9
 */

@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class PositionMapperTest {

    @Autowired
    private PositionMapper positionMapper;

    @Test
    public void test1(){
        try {
            System.out.println(positionMapper.findPositionById(1).getDepartment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        PositionFind positionFind = new PositionFind();
        positionFind.andNameLike("人");
        positionFind.andDepartmentId(1);
        try {
            System.out.println(positionMapper.findPositionByCustomer(positionFind));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        /*ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        positionMapper.deleteList(ids);*/
        try {
            System.out.println(positionMapper.showDelete());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

