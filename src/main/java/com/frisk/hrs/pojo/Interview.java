package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author frisktale
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interview {
    private Integer id;


    private Date deliveryTime;


    private Date interviewTime;

    private String status;

    private String userStatus;

    private Boolean isRead;

}