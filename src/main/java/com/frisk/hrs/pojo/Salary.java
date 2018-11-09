package com.frisk.hrs.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary implements Serializable {
    private Integer id;

    private Integer employeeId;

    @JsonFormat(pattern = "yyyy年MM月dd日", locale = "zh" , timezone="GMT+8")
    private Date time;

    private Double basic;

    private Double performance;

    private Double overtime;

    private Double rewards;

    private Boolean isDisagree;

    private Boolean isProcess;


}