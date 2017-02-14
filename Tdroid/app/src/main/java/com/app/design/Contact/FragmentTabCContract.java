package com.app.design.Contact;


import core.base.BasePresenter;
import core.base.IModel;
import core.base.IView;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public interface FragmentTabCContract {
    interface Model extends IModel {
        /*//请求获取图片
        Observable<List<PhotoGirl>> getPhotosListData(int size, int page);

        Observable<GithubTest> getGitHubTestData();

        Observable<List<NewsSummary>> getNewsList(final String type, final String id, final int startPage);*/
    }

    interface View extends IView {
    }

    abstract class Presenter extends BasePresenter {
        public Presenter(View mView, Model model) {
            super(mView, model);
        }
    }
}
