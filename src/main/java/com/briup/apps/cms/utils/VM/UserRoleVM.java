package com.briup.apps.cms.utils.vm;

import java.util.List;

/*
 * 用户角色*
 */

public class UserRoleVM {
    private Long id;
    private List<Long> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
