package com.app.design.app;


import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import core.base.BaseFragment;
import core.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AppBaseFragment<SV extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<SV> {
    protected P mPresenter;
    protected boolean isPrepared = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        loadData();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = initPresenter();
        setUpViews(savedInstanceState);
//        loadData();
    }

    @Override
    protected void onVisible() {
        if (!isPrepared || !mIsVisible)
            return;
        super.onVisible();
    }

    protected abstract P initPresenter();

    /*protected P initPresenter() {
        P instance = null;
        Class<P> z = (Class<P>) ((ParameterizedType) (getClass()
                .getGenericSuperclass())).getActualTypeArguments()[1];
        Constructor[] cons = z.getDeclaredConstructors();
        try {
            Constructor c = cons[0];
            Class[] parameterTypes = c.getParameterTypes();
            Class V = parameterTypes[0];
            Class M = parameterTypes[1];
            //IV IM
            Method method = M.getDeclaredMethod("getInstance", null);
            Object m = method.invoke(null);
            instance = (P) c.newInstance(this, m);
            LogUtils.i(instance.getClass().getConstructors().toString());
        } catch (NoSuchMethodException e) {
            LogUtils.e(e, "keep with sington method name = getInstance()");
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            LogUtils.e(e, "keep with the same Constructor(? extends IV，? extends IM)");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            LogUtils.e(e, "keep with the same Constructor(? extends IV，? extends IM)");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LogUtils.e(e, "keep with the same Constructor(? extends IV，? extends IM)");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }
*/
    protected void setProgress(boolean show, String text, boolean isEmpty) {
        if (show) {
            mLEElayout.showLoading(text, isEmpty);
        } else {
            mLEElayout.showSuccess();
        }
    }

    protected abstract void setUpViews(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        if (mPresenter != null) mPresenter.detachView();
    }


}
