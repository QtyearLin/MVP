package core.callback;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by Tyearlin on 2014/6/22
 */
public interface BaseActivityContentControl<T extends ViewDataBinding> {

    /**
     * 获取activity根部视图
     */
    T getViewDataBinding();

    View getContainerView(ViewDataBinding viewDataBinding, int containerId);

}
