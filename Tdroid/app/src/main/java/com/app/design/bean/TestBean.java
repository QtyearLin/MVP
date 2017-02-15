package com.app.design.bean;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Qtyearlin on 2017/2/10.
 */
@Entity
public class TestBean {
    @Id
    @NonNull
    private Long id;
    private String titel;

    public TestBean(long id, String titel) {
        this.id = id;
        this.titel = titel;
    }

    @Generated(hash = 1711198661)
    public TestBean(@NonNull Long id, String titel) {
        this.id = id;
        this.titel = titel;
    }

    @Generated(hash = 2087637710)
    public TestBean() {
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
