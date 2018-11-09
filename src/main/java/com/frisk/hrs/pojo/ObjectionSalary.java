package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectionSalary {
    private Integer id;

    private String content;

    private Double money;

    private Date reissueTime;

    private Boolean isExecute;

}