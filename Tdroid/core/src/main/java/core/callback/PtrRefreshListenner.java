package core.callback;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public abstract class PtrRefreshListenner extends PtrDefaultHandler {
    public abstract void onRefresh(PtrFrameLayout frame);

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        onRefresh(frame);
    }

}