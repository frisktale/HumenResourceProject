package com.frisk.hrs.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class EmployeeCustom {
    private Employee employee;
    private Position position;
    private Department department;
    private User user;

    private List<Train> train;
}
