package core.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import core.R;
import core.baseapp.AppManager;
import core.view.CoreLEELayout;

/**
 * Created by Tyearlin on 16/12/10.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    // 布局view
    private int mDefaultStatusBarColor = R.color.colorStatusBarTheme;
    public CoreLEELayout mLEElayout;
    protected P mPresenter;

    public AppCompatActivity mContext;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }


    protected abstract P initPresenter();//不设置则P无效


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initLEE();
//        mPresenter = GenericUtil.getT(this, 0);
        mPresenter = initPresenter();
    }

    /**
     * // 设置透明状态栏
     */
    protected void setStatusBarColor(boolean enable) {
        if (enable)
            StatusBarUtil.setColor(this, getResources().getColor(mDefaultStatusBarColor), 0);
    }


    protected void initLEE() {
        mLEElayout = getView(R.id.lay_lee);
        mLEElayout.bindView(getLEEDependView());
        // 点击加载失败布局
        mLEElayout.setOnRefreshClick(new BaseOnclick() {
            @Override
            protected void onNoDoubleClick(View v) {
                mLEElayout.showLoading("", true);
                onRefresh();
            }
        });
    }

    protected abstract View getLEEDependView();


    public void setDefaultStatusBarColor(int mDefaultStatusBarColor) {
        this.mDefaultStatusBarColor = mDefaultStatusBarColor;
    }


    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        AppManager.getAppManager().removeActivity(this);
    }


}
