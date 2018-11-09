package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String education;

    private String tel;

    private String email;

    private String politicalStatus;

    private String lastJob;

    private String exp;

    private Double expectedSalaryLow;

    private Double expectedSalaryHigh;

    private String hobby;

    private Integer userId;
}