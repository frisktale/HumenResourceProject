package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rewards {
    private Integer id;

    private Date time;

    private String content;

    private Integer employeeId;

    private Boolean isDelete;

    private Double money;
}