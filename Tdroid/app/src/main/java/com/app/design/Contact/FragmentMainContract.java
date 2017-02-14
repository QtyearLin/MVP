package com.app.design.Contact;


import com.app.design.bean.GithubTest;
import com.app.design.bean.NewsSummary;
import com.app.design.bean.PhotoGirl;

import java.util.List;

import core.base.BasePresenter;
import core.base.IModel;
import core.base.IView;
import rx.Observable;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public interface FragmentMainContract {
    interface Model extends IModel {
    }

    interface View extends IView {
    }

    abstract class Presenter extends BasePresenter {
        public Presenter(View mView, Model model) {
            super(mView, model);
        }
    }
}
