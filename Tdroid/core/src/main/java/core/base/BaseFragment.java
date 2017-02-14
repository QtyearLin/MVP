package core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

import core.R;
import core.callback.LEEControl;
import core.utils.LogUtils;
import core.view.CoreLEELayout;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 是没有title的Fragment
 * 考虑到进度条布局位置
 * 你可能需要手动调整视图位置
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {

    // 布局view
    protected SV bindingView;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    // 内容布局
    protected RelativeLayout mContainer;
    protected CoreLEELayout mLEElayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getContentViewLayoutID(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (RelativeLayout) ll.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        initLEE(ll);
        return ll;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     * 而setUserVisibleHint是在onCreateView之前调用的，那么在视图未初始化的时候，
     * 在lazyLoad当中就使用的话，就会有空指针的异常。而把lazyLoad抽离成一个方法，那么它的子类就可以这样做：
     */
    protected abstract void loadData();

    protected void onVisible() {
        LogUtils.d("onVisible---->loadData");
        loadData();
    }

    private void initLEE(View ll) {
        mLEElayout = getView(ll, R.id.lay_lee);
        mLEElayout.bindView(bindingView.getRoot());
        mLEElayout.showLoading();
        // 点击加载失败布局
        mLEElayout.setOnRefreshClick(new BaseOnclick() {
            @Override
            protected void onNoDoubleClick(View v) {
                mLEElayout.showLoading("", true);
                onRefresh();
            }
        });
    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 布局
     */
    public abstract int getContentViewLayoutID();

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {
        loadData();
    }

    /**
     * 调整加载视图的位置
     * 默认顶部距离80dp
     *
     * @param gravity
     * @param paddingTop
     */
  /*  protected void setProgressBarXY(int gravity, int paddingTop) {
        mLlProgressBar.setGravity(gravity);
        mLlProgressBar.setPadding(mLlProgressBar.getPaddingLeft(), paddingTop,
                mLlProgressBar.getPaddingRight(), mLlProgressBar.getPaddingBottom());
    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
