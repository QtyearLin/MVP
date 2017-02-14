package core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import core.R;
import core.callback.EmptyViewControl;

/**
 * Created by Tyearlin on 2014/6/22
 */
public class CoreLEELayout extends FrameLayout implements EmptyViewControl {

    protected View mEmptyView;
    protected View mBindView;
    protected View mErrorView;
    protected View mLoadingView;

    protected Button mBtnReset;

    protected TextView mEmptyText;
    protected TextView tvLoadingText;

    protected Context mContext;
    private AnimationDrawable mAnimationDrawable;

    public CoreLEELayout(Context context) {
        this(context, null);
    }

    public CoreLEELayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreLEELayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context, attrs);
        //全部隐藏
        setGone();
    }

    /**
     * <p>style setting<br>
     * core_layout_empty :empty view <br>
     * core_layout_loading :loading view <br>
     * core_layout_error :ic_s_error view</p>
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居中
        params.gravity = Gravity.CENTER;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StyleEmptyViewControl, 0, 0);
        //数据为空时的布局
        int emptyLayout = ta.getResourceId(R.styleable.StyleEmptyViewControl_style_elEmptyLayout, R.layout
                .core_layout_empty);
        mEmptyView = View.inflate(context, emptyLayout, null);
        mEmptyText = (TextView) mEmptyView.findViewById(R.id.tvEmptyText);
        addView(mEmptyView, params);

        //加载中的布局
        int loadingLayout = ta.getResourceId(R.styleable.StyleEmptyViewControl_style_elLoadingLayout, R.layout
                .core_layout_loading);
        mLoadingView = View.inflate(context, loadingLayout, null);
        tvLoadingText = (TextView) mLoadingView.findViewById(R.id.tvLoadingText);
        addView(mLoadingView, params);

        //错误时的布局
        int errorLayout = ta.getResourceId(R.styleable.StyleEmptyViewControl_style_elErrorLayout, R.layout
                .core_layout_error);
        mErrorView = View.inflate(context, errorLayout, null);
        mBtnReset = (Button) mErrorView.findViewById(R.id.btnReset);
        addView(mErrorView, params);
        mAnimationDrawable = (AnimationDrawable) mLoadingView.findViewById(R.id.imvLoading).getBackground();
    }

    /**
     * @param resId
     */
    @Override
    public void setEmptyView(int resId) {
        setEmptyView(View.inflate(mContext, resId, null));
    }

    /**
     * @param v
     */
    @Override
    public void setEmptyView(View v) {
        if (indexOfChild(mEmptyView) != -1) {
            removeView(mEmptyView);
        }
        mEmptyView = v;
        addView(mEmptyView);
        setGone();
    }

    /**
     * @param view
     */
    @Override
    public void bindView(View view) {
        mBindView = view;
    }

    /**
     * @param emptyText
     */
    @Override
    public void showEmpty(String emptyText) {
        // 停止动画
        if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        setGone();
        mEmptyView.setVisibility(View.VISIBLE);
        if (null != emptyText) mEmptyText.setText(emptyText);
    }

    /**
     *
     */
    final public void showError() {
        showError(null);
    }

    /**
     * @param text
     */
    @Override
    public void showError(String text) {
        // 停止动画
        if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) mBtnReset.setText(text);
        setGone();
        mErrorView.setVisibility(View.VISIBLE);
    }


    final public void showLoading() {
        showLoading(getResources().getString(R.string.loading), true);
    }

    /**
     * @param text
     */
    final public void showLoading(String text) {
        showLoading(text, true);
    }

    /**
     * @param text
     * @param isEmpty
     */
    @Override
    public void showLoading(String text, boolean isEmpty) {
        if (mBindView != null && isEmpty)
            mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) tvLoadingText.setText(text);
        setGone();
        mLoadingView.setVisibility(View.VISIBLE);
        // 加载动画
        // 默认进入页面就开启动画
        if (null != mAnimationDrawable && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }


    /**
     * @param listener
     */
    @Override
    public void setOnRefreshClick(OnClickListener listener) {
        mBtnReset.setOnClickListener(listener);
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess() {
        if (mBindView != null) mBindView.setVisibility(View.VISIBLE);
        setGone();
    }

}
