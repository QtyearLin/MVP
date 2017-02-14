package core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Tyearlin on 16/12/10.
 */
public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity {

    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
    }

    protected abstract T getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }


}
