package com.frisk.hrs.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
public class TrainCustom {
    private Train train;
    private Department department;
    private Position position;
    private List<Employee> employees;
}
