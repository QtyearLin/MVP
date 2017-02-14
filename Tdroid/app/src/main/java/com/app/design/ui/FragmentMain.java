package com.app.design.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.app.design.Contact.FragmentMainContract;
import com.app.design.R;
import com.app.design.app.AppBaseFragment;
import com.app.design.bean.PhotoGirl;
import com.app.design.databinding.FragmentMainBinding;
import com.app.design.model.PhotosListModel;
import com.app.design.presenter.FragmentMainPresenter;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.base.BaseViewPageTagFragmentAdapter;
import core.base.RxCodeConstants;
import core.base.ScrollAwareFABBehavior;
import core.base.ScrollScaleFABBehavior;
import core.baserx.RxBus;
import core.baserx.RxBusBaseMessage;
import core.entity.TabEntity;
import core.utils.ToastUtils;
import rx.functions.Action1;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMain extends AppBaseFragment<FragmentMainBinding, FragmentMainPresenter> implements
        FragmentMainContract.View {

    private static final String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private static final Class[] mTags = {FragmentA.class, FragmentB.class,
            FragmentC.class, FragmentD.class};
    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ScrollAwareFABBehavior mScrollAwareFABBehavior;


    @Override
    protected void loadData() {
        setupTabs();
        isPrepared = true;
        setProgress(false,null,false);
    }

    private void setupTabs() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        bindingView.tabLayout.setTabData(mTabEntities);
        setUpTabMsgInfo();
    }

    private void setUpTabMsgInfo() {
        //两位数
        bindingView.tabLayout.showMsg(0, 55);
        bindingView.tabLayout.setMsgMargin(0, -5, 5);

        //三位数
        bindingView.tabLayout.showMsg(1, 100);
        bindingView.tabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        bindingView.tabLayout.showDot(2);
        MsgView rtv_2_2 = bindingView.tabLayout.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }
        //设置未读消息背景
        bindingView.tabLayout.showMsg(3, 5);
        bindingView.tabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = bindingView.tabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    Random mRandom = new Random();

    @Override
    protected FragmentMainPresenter initPresenter() {
        return new FragmentMainPresenter(this, PhotosListModel.getInstance());
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
        mScrollAwareFABBehavior = ScrollAwareFABBehavior.from(bindingView.fab);
        bindingView.viewpager.addOnPageChangeListener(mOnPageChangeListener);
        bindingView.viewpager.setAdapter(new BaseViewPageTagFragmentAdapter(getChildFragmentManager(), mTags, null));
        bindingView.viewpager.setCurrentItem(0);
        bindingView.viewpager.setOffscreenPageLimit(4);
        bindingView.tabLayout.setOnTabSelectListener(mOnTabSelectedListener);
        mPresenter.addSubscribe(RxBus.getDefault().toObservable(RxCodeConstants.SNACKBAR_FLAOT, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage integer) {
                        ToastUtils.ShortSnackbar(bindingView.coordinatorLayout, R.string.quit_app).show();
                    }
                }));
        mScrollAwareFABBehavior.setOnStateChangedListener(new ScrollScaleFABBehavior.OnStateChangedListener() {
            @Override
            public void onChanged(boolean isShow) {
                if (Build.VERSION.SDK_INT >= 14) {
                    ViewCompat.animate(bindingView.tabLayout).translationY(!isShow ? bindingView.tabLayout.getHeight()
                            : 0)
                            .setInterpolator
                                    (new AccelerateDecelerateInterpolator()).withLayer().start();
                } else {

                }
            }
        });
    }

    private OnTabSelectListener mOnTabSelectedListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            bindingView.viewpager.setCurrentItem(position);
        }

        @Override
        public void onTabReselect(int position) {
            if (position == 0) {
                bindingView.tabLayout.showMsg(0, mRandom.nextInt(100) + 1);
            }
        }
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bindingView.tabLayout.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void setProgress(boolean show, String text, boolean isEmpty) {
        super.setProgress(show,text,isEmpty);
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_main;
    }


}
