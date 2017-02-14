package com.app.design.Contact;


import core.base.BasePresenter;
import core.base.IModel;
import core.base.IPresenter;
import core.base.IView;

/**
 * Created by Lin on 2016/2/4.
 */
public interface TestContract {

    interface Model extends IModel {
        String test();
    }

    interface View extends IView {
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public Presenter(View mView, Model model) {
            super(mView, model);
        }
        public abstract void test();
    }
}
