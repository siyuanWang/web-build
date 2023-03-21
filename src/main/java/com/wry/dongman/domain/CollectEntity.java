package com.wry.dongman.domain;

import java.util.Date;

public class CollectEntity {
    private Long id;

    private Long userId;

    private Long skuId;

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
        return "CollectEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", skuId=" + skuId +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
