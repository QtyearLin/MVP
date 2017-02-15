package com.app.design;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.app.design.Contact.TestContract;
import com.app.design.app.AppBaseActivity;
import com.app.design.databinding.ActivityMainBinding;
import com.app.design.model.TestModel;
import com.app.design.presenter.TestPresenter;
import com.app.design.ui.FragmentLeftSlide;
import com.app.design.ui.FragmentMain;
import com.app.design.ui.FragmentRightSlide;

import core.base.RxCodeConstants;
import core.baserx.RxBus;
import core.baserx.RxBusBaseMessage;
import core.utils.FragmentUtils;
import core.utils.QuitUitls;
import rx.functions.Action1;


public class MainActivity extends AppBaseActivity<ActivityMainBinding, TestPresenter> implements TestContract.View {

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initToolbar();
        initToogle();
        initLeftMenu();
        initRightMenu();
        initContextView();
    }

    private void initToolbar() {
        setToolbarEnable(true);
    }


    @Override
    public void onBackPressed() {
        if (mBindView.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mBindView.drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            QuitUitls.quitHelp(mContext, new QuitUitls.QuitCallback() {
                @Override
                public void onQuitBack() {
                    RxBus.getDefault().post(RxCodeConstants.SNACKBAR_FLAOT, new RxBusBaseMessage(1, 0));
                }
            });
        }
    }

    private void initRightMenu() {
        FragmentUtils.addFragmentByTag(R.id.lay_right_container, FragmentRightSlide.class, getSupportFragmentManager(),
                false, getSupportFragmentManager().beginTransaction());
    }

    private void initToogle() {
        mBindView.drawerLayout.openDrawer(Gravity.LEFT, true);
        mBindView.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        RxBus.getDefault().toObservable(RxCodeConstants.DRAWER_CONTROL, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage integer) {
                        if (integer.getCode() > 0) {
                            mBindView.drawerLayout.openDrawer(Gravity.LEFT);
                        } else {
                            mBindView.drawerLayout.closeDrawer(Gravity.LEFT);
                        }

                    }
                });
    }

    private void initContextView() {
        FragmentUtils.addFragmentByTag(R.id.content_container, FragmentMain.class, getSupportFragmentManager(),
                false, getSupportFragmentManager().beginTransaction());
    }

    private void initLeftMenu() {
        FragmentUtils.addFragmentByTag(R.id.lay_left_container, FragmentLeftSlide.class, getSupportFragmentManager(),
                false, getSupportFragmentManager().beginTransaction());
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected TestPresenter initPresenter() {
        return new TestPresenter(this, TestModel.getInstance());
    }


    @Override
    public void setProgress(boolean show, String text, boolean isEmpty) {
        if (show) {
            mLEElayout.showLoading(text, isEmpty);
        } else {
            mLEElayout.showSuccess();
        }
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
