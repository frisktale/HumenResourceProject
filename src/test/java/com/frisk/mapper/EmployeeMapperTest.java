package com.frisk.mapper;

import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.pojo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test1(){
        EmployeeFind employeeFind = new EmployeeFind();
        employeeFind.andPosIdIs(3);
        try {
            List<EmployeeCustom> employee = employeeMapper.findEmployeeByCustomer(employeeFind);
            System.out.println(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        Employee employee = new Employee(0, "qqq", "试用",4000d);
        EmployeeCustom customer = new EmployeeCustom();
        customer.setEmployee(employee);
        try {
            employeeMapper.insertEmployee(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test3(){
        Employee employee = new Employee(3, "qqqq", "在职",4000d);
        Department department = new Department();
        department.setId(1);
        Position position = new Position();
        position.setId(1);
        EmployeeCustom customer = new EmployeeCustom();
        customer.setEmployee(employee);
        customer.setPosition(position);
        customer.setDepartment(department);
        try {
            employeeMapper.updateEmployee(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
