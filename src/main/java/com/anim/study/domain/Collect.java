package com.anim.study.domain;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date collectTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", collectTime=" + collectTime +
                '}';
    }
}