package core.view;

import android.content.Context;
import android.util.AttributeSet;

import core.R;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * 默认的M样式下拉刷新
 */
public class CorePtrMLayout extends CorePtrLayout {

    protected static final int REFRESH_AUTO_DELAY = 300;
    private MaterialHeader mPtrClassicHeader;
    public boolean mAutoRefresh = false;

    public CorePtrMLayout(Context context) {
        super(context);
        initViews();
    }

    public CorePtrMLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CorePtrMLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    public void initViews() {
        mPtrClassicHeader = new MaterialHeader(getContext());
        /**
         * 设置默认参数
         */
        int[] colors = getResources().getIntArray(R.array.google_colors);
        mPtrClassicHeader.setColorSchemeColors(colors);
        mPtrClassicHeader.setLayoutParams(new LayoutParams(-1, -2));
        mPtrClassicHeader.setPadding(0, dip2px(getContext(), 15f), 0, dip2px(getContext(), 10f));
        mPtrClassicHeader.setPtrFrameLayout(this);

        setPinContent(true);
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);

        setLoadingMinTime(350);
        setDurationToCloseHeader(200);

    }

    public MaterialHeader getHeader() {
        return mPtrClassicHeader;
    }

    @Override
    public boolean isAutoRefresh() {
        return mAutoRefresh;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        mAutoRefresh = autoRefresh;
        autoRefreshDelay();
    }

    /**
     * 增加默认时间
     */
    public void autoRefreshNow() {
        post(new Runnable() {
            @Override
            public void run() {
                autoRefresh(true);
            }
        });

    }

    /**
     * 增加默认时间
     */
    public void autoRefreshDelay() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                autoRefresh(true);
            }
        }, REFRESH_AUTO_DELAY);
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
