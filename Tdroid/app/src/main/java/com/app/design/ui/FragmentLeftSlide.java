package com.app.design.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.design.R;
import com.app.design.app.AppBaseFragment;
import com.app.design.databinding.FragmentLeftSlideBinding;
import com.app.design.presenter.TestPresenter;


import core.base.BaseOnclick;
import core.base.RxCodeConstants;
import core.baserx.RxBus;
import core.baserx.RxBusBaseMessage;
import core.image.ImageLoader;
import core.image.glide.GlideImageConfig;
import rx.Observable;


public class FragmentLeftSlide extends AppBaseFragment<FragmentLeftSlideBinding, TestPresenter> {


    @Override
    protected void loadData() {
        mLEElayout.showSuccess();
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_left_slide;
    }

    @Override
    protected TestPresenter initPresenter() {
        return null;
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
        ImageLoader.getInstance().displayImage(this, new GlideImageConfig()
                .setImageView(bindingView.ivAvatar)
                .setUrl("http://upload.jianshu" +
                        ".io/users/upload_avatars/1159853/428cb459ad18?imageMogr2/auto-orient/strip|imageView2/1/w" +
                        "/240/h/240"));

        bindingView.llNavAbout.setOnClickListener(new BaseOnclick() {
            @Override
            protected void onNoDoubleClick(View v) {
                RxBus.getDefault().post(RxCodeConstants.DRAWER_CONTROL,new RxBusBaseMessage<String>(0,null));
                // TODO: 2017/2/7
            }
        });
        bindingView.llHeaderBg.setOnClickListener(new BaseOnclick() {
            @Override
            protected void onNoDoubleClick(View v) {
                RxBus.getDefault().post(RxCodeConstants.DRAWER_CONTROL,new RxBusBaseMessage<String>(0,null));
                // TODO: 2017/2/7
            }
        });
        bindingView.llNavHomepage.setOnClickListener(new BaseOnclick() {
            @Override
            protected void onNoDoubleClick(View v) {
                RxBus.getDefault().post(RxCodeConstants.DRAWER_CONTROL,new RxBusBaseMessage<String>(0,null));
                // TODO: 2017/2/7
            }
        });
    }
}
