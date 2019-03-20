package com.ssm.vo;

import com.ssm.pojo.Role;
import com.ssm.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己封装的 javabean  为了显示用的
 */
public class UserRoleVO extends User {
    private List<Long> rids=new ArrayList<>();
    private List<String> rnames=new ArrayList<>();
    private List<Role> roleList=new ArrayList<>();

    @Override
    public String toString() {
        return "UserRoleVO{" +
                super.toString()+
                "rids=" + rids +
                ", rnames=" + rnames +
                ", roleList=" + roleList +
                '}';
    }

    public List<Long> getRids() {
        return rids;
    }

    public void setRids(List<Long> rids) {
        this.rids = rids;
    }

    public List<String> getRnames() {
        return rnames;
    }

    public void setRnames(List<String> rnames) {
        this.rnames = rnames;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
