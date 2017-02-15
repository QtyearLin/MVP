package com.app.design.bean;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lin on 2017/1/18.
 */
@Entity
public class User implements MultiItemEntity{
    @Id
    @NonNull
    private Long userId;
    private String name;

    @Generated(hash = 2075353056)
    public User(@NonNull Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
