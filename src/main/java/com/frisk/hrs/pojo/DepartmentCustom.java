package com.frisk.hrs.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class DepartmentCustom {
    private Department department;
    private Employee leader;
    private List<Employee> employees;
    private List<Position> positions;
}
