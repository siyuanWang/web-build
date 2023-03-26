package com.wry.dongman.domain;

import java.util.Date;

public class OrderEntity {
    private Long id;

    private Long userId;

    private Long skuId;

    private String position;

    private Date ctime;

    private Date utime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                "userId=" + userId +
                "position=" + position +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
