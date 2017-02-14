package com.app.design.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.design.R;
import com.app.design.app.AppBaseFragment;
import com.app.design.databinding.FragmentTabCBinding;
import com.app.design.presenter.TestPresenter;

import core.base.BaseOnclick;
import core.base.RxCodeConstants;
import core.baserx.RxBus;
import core.baserx.RxBusBaseMessage;
import core.image.ImageLoader;
import core.image.glide.GlideImageConfig;


public class FragmentC extends AppBaseFragment<FragmentTabCBinding, TestPresenter> {


    @Override
    protected void loadData() {
        mLEElayout.showSuccess();
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_tab_c;
    }

    @Override
    protected TestPresenter initPresenter() {
        return null;
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
    }
}
