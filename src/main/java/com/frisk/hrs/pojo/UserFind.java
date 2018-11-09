package com.frisk.hrs.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserFind extends User {
    private Employee employee;

    private Integer resumeId;

    public void andUsernameIs(String name) {
        super.setUsername(name);
    }

    public void andUsernameLike(String name) {
        super.setUsername("%" + name + "%");
    }

    public void andPasswordIs(String password) {
        setPassword(password);
    }

    public void andTypeIs(Integer type) {
        setType(type);
    }

    public void andResumeIdIs(Integer resumeId) {
        setResumeId(resumeId);
    }

    public void andEmployeeIdIs(Integer id) {
        employee = new Employee();
        employee.setId(id);
    }
}
