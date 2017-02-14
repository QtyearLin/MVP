package com.app.design.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Lin on 2017/1/18.
 */

public class LeftMenuBean implements MultiItemEntity {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;
    private int id;
    private int type;
    private String titel;
    private int icon;

    public LeftMenuBean(int id, int type, String titel, int icon) {
        this.id = id;
        this.type = type;
        this.titel = titel;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
