package com.app.design.Contact;


import com.app.design.bean.FrontpageBean;
import com.app.design.bean.NewsSummary;

import java.util.List;

import core.base.BasePresenter;
import core.base.IModel;
import core.base.IView;
import rx.Observable;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public interface FragmentTabAContract {
    interface Model extends IModel {
        Observable<List<NewsSummary>> getFeeds(String id, int page);

        Observable<List<FrontpageBean.DataBeanX.DataBean>> getBanners();

    }

    interface View extends IView {
        void setBanner(List<FrontpageBean.DataBeanX.DataBean> list);

        void setFeeds(List<NewsSummary> list);
    }

    abstract class Presenter<V extends View, M extends Model> extends BasePresenter<View, Model> {
        public Presenter(V mView, M model) {
            super(mView, model);
        }

        public abstract void loadBanner();

        public abstract void loadFeeds(String id, int page);

    }
}
