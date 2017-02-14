package core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import core.R;
import core.callback.BaseActivityContentControl;
import core.databinding.ActivityBaseTopToolbarBinding;

/**
 * Created by Tyearlin on 16/12/10.
 * <p>cover toolbar--> toolbar gone <br>
 * 自定义layout toolbar cover<</p>
 */
public abstract class BaseActivityTopToolBar<SV extends ViewDataBinding,P extends BasePresenter> extends BaseActivity<P>
        implements
        BaseActivityContentControl {

    // 布局view
    protected SV mBindView;
    protected ActivityBaseTopToolbarBinding mBaseBinding;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        addContentViewToBase(layoutResID);
        setToolBar();
        initLEE();
    }


    private void addContentViewToBase(int layoutResID) {
        mBaseBinding = getViewDataBinding();
        mBindView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mBindView.getRoot().setLayoutParams(params);
        ViewGroup mContainer = getContainerView(mBaseBinding, R.id.container);
        mContainer.addView(mBindView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());
    }


    @Override
    protected View getLEEDependView() {
        return null != mBindView ? mBindView.getRoot() : getWindow().getDecorView();
    }


    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mBaseBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.setTitle(text);
    }

    public void setToolbarEnable(boolean show) {
        mBaseBinding.toolBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    public ActivityBaseTopToolbarBinding getViewDataBinding() {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base_top_toolbar, null, false);
    }

    @Override
    public ViewGroup getContainerView(ViewDataBinding viewDataBinding, int containerId) {
        return (ViewGroup) viewDataBinding.getRoot().findViewById(containerId);
    }
}
