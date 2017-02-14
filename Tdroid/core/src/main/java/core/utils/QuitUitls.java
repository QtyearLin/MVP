package core.utils;

import android.app.Activity;

/**
 * Created by Qtyearlin on 2017/2/7.
 */

public class QuitUitls {
    private static final int TIME = 2000;
    private static long mPressedTime = 0;

    public static void quitHelp(Activity activity,QuitCallback quictCallback) {
        long mNowTime = System.currentTimeMillis();
        if (mNowTime - mPressedTime > TIME) {
            quictCallback.onQuitBack();
            mPressedTime = System.currentTimeMillis();
        } else {
            activity.finish();
        }
    }
    public interface  QuitCallback {
        void onQuitBack();
    }
}
