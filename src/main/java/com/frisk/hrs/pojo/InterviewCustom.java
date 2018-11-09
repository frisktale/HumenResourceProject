package com.frisk.hrs.pojo;

import lombok.Data;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@Data
public class InterviewCustom {
    private Interview interview;
    private User user;
    private Resume resume;
    private Employee employee;
    private Offers offers;
}
