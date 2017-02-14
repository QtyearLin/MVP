package com.app.design.app;


import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import core.base.BaseFragment;
import core.base.BasePresenter;
import core.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AppBaseFragment<SV extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<SV> {
    protected P mPresenter;
    protected boolean isPrepared = false;
    protected boolean isLoadFinished = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = initPresenter();
        setUpViews(savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d("onActivityCreated");
        isPrepared = true;
        loadData();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d("onViewCreated");
    }

    @Override
    protected void onVisible() {
        if (!isPrepared || !mIsVisible || isLoadFinished)
            return;
        super.onVisible();
    }

    protected abstract P initPresenter();


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
        LogUtils.d("onDestroyView");
        isPrepared = false;
        isLoadFinished = false;
        if (mPresenter != null) mPresenter.detachView();
    }

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


}
