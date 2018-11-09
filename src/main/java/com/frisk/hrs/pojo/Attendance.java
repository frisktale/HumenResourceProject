package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    private Integer id;

    private Date startWorkTime;

    private Date offWorkTime;

    private String status;

    private Integer employeeId;

    private Boolean isLate;

    private Boolean isAbsent;

}