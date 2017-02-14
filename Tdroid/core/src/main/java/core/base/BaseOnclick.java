package core.base;

/**
 * 避免在0.5秒内出发多次点击
 * Created by Lin on 2017/1/17.
 */

import android.view.View;

import java.util.Calendar;

public abstract class BaseOnclick implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = v.getId();
        if (id != mId) {
            id = mId;
            lastClickTime = currentTime;
            onNoDoubleClick(v);
            return;
        }
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);
}
