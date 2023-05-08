package com.anim.study.domain;

import java.util.Date;

public class CollectRole extends Role{

    private Date collectTime;

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
