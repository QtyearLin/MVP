package com.app.design.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.design.R;
import com.app.design.app.AppBaseFragment;
import com.app.design.databinding.FragmentLeftSlideBinding;
import com.app.design.databinding.FragmentRightSlideBinding;
import com.app.design.presenter.TestPresenter;

import core.base.BaseFragment;
import core.utils.ToastUtils;


public class FragmentRightSlide extends AppBaseFragment<FragmentRightSlideBinding,TestPresenter> {

    @Override
    protected void loadData() {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_right_slide;
    }

    @Override
    protected TestPresenter initPresenter() {
        return null;
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
        bindingView.testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.ShortSnackbar(view,R.string.abc_action_bar_home_description).show();
            }
        });
    }
}
