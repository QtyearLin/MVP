package com.app.design.app;

import com.app.design.BuildConfig;

import core.baseapp.BaseApplication;
import core.utils.LogUtils;

/**
 * Created by Lin on 2017/2/4.
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
//        CacheLoader.getInstance(this);
    }
}
