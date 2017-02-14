package core.view;

import android.content.Context;
import android.util.AttributeSet;

import core.callback.PtrRefreshListenner;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by QTylin on 2016/1/4.
 * TODO:  增加触发移动事件的最短距离机制
 */
public class CorePtrLayout extends PtrFrameLayout {
    public CorePtrLayout(Context context) {
        super(context);
    }

    public CorePtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CorePtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setonRefreshListenner(final PtrRefreshListenner listenner) {
        setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                listenner.onRefresh(frame);
            }
        });

    }


}
