package com.app.design.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.design.R;
import com.app.design.app.AppBaseFragment;
import com.app.design.databinding.FragmentTabDBinding;
import com.app.design.presenter.TestPresenter;



public class FragmentD extends AppBaseFragment<FragmentTabDBinding, TestPresenter> {


    @Override
    protected void loadData() {
        mLEElayout.showSuccess();
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_tab_d;
    }

    @Override
    protected TestPresenter initPresenter() {
        return null;
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
    }
}
