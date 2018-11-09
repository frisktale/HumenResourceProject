package com.frisk.hrs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCustom {
    private User user;
    private Employee employee;
    private Resume resume;
}
