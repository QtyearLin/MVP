package core.view;

import android.content.Context;
import android.util.AttributeSet;

public class CoreOldPtrLayout extends CorePtrLayout {

    private CorePtrOldHeader mPtrClassicHeader;

    public CoreOldPtrLayout(Context context) {
        super(context);
        initViews();
    }

    public CoreOldPtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CoreOldPtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new CorePtrOldHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
    }

    public CorePtrOldHeader getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }


    public void setAutoRefresh(boolean b) {
        setPullToRefresh(b);
    }
}
