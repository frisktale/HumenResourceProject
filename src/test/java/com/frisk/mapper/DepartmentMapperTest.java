package com.frisk.mapper;

import com.frisk.hrs.mapper.DepartmentMapper;
import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.pojo.Department;
import com.frisk.hrs.pojo.DepartmentCustom;
import com.frisk.hrs.pojo.DepartmentFind;
import com.frisk.hrs.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@ExtendWith(SpringExtension.class) //使用junit5进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-dao-config.xml", "classpath:spring/spring-service-config.xml",
        "classpath:spring/spring-tx-config.xml"})
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test1(){
        try {
            System.out.println(departmentMapper.findDepartmentById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        DepartmentFind departmentFind = new DepartmentFind();
//        departmentFind.andNameIs("开发部");
//        departmentFind.andNameLike("开");

        try {
            System.out.println(departmentMapper.findDepartmentByCustomer(departmentFind).get(0).getPositions());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        DepartmentCustom departmentCustom = new DepartmentCustom();
        Department department = new Department(1, "测试部", new Date());
        Employee employee = new Employee();
        employee.setId(2);
        departmentCustom.setDepartment(department);
        departmentCustom.setLeader(employee);
        try {
            departmentMapper.updateDepartment(departmentCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        /*ArrayList<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(3);
        departmentMapper.deleteList(ids);*/
        /*ArrayList<Integer> ids = new ArrayList<>();
        ids.add(2);
        departmentMapper.recoverDepartment(ids);*/
        try {
            departmentMapper.cleanDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
