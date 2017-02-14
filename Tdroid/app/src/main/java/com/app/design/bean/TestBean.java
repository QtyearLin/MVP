package com.app.design.bean;

/**
 * Created by Qtyearlin on 2017/2/10.
 */

public class TestBean {
    int id;
    String titel;

    public TestBean(int id, String titel) {
        this.id = id;
        this.titel = titel;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }
}
