/**
 * created by lin, 16/3/13
 * Copyright (c) 2016,  All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package com.app.design.app;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.design.MainActivity;
import com.app.design.R;

import core.base.BaseActivityTopToolBar;
import core.base.BasePresenter;

/**
 * @author qtyearlin
 *         create at 2016/4/30 11:33
 */


public abstract class AppBaseActivity<SV extends ViewDataBinding,P extends BasePresenter> extends BaseActivityTopToolBar<SV,P> {
    private final int APPBAR_COLOR = R.color.colorStatusBarTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
        } else {
            throw new IllegalArgumentException("You must return PtrRefreshListenner right contentView layout resource Id");
        }
        setStatusBarColor(true);
        initViewsAndEvents(savedInstanceState);
    }


    @Override
    protected void setStatusBarColor(boolean enable) {
        if (!enable)
            return;
        if (this instanceof MainActivity) {
//            setDefaultStatusBarColor(APPBAR_COLOR);
        } else  {
            super.setStatusBarColor(enable);
        }

    }


    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    protected abstract int getContentViewLayoutID();


}
