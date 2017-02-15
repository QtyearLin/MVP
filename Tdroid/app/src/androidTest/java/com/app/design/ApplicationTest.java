package com.app.design;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.app.design.app.AppDb;
import com.app.design.bean.User;
import com.greendao.gen.DaoSession;
import com.greendao.gen.UserDao;

import org.junit.Test;

import java.util.List;

import core.baseapp.AppConfig;
import core.utils.LogUtils;

/**
 * <PtrRefreshListenner href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</PtrRefreshListenner>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

}