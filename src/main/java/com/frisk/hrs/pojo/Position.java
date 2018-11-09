package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position  implements Serializable {
    private Integer id;

    private String name;


    private Date createTime;

}