package com.app.design.presenter;


import com.app.design.Contact.FragmentTabAContract;
import com.app.design.bean.FrontpageBean;
import com.app.design.bean.NewsSummary;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Lin on 16/5/26.
 */
public class FragmentAPresenter extends FragmentTabAContract.Presenter<FragmentTabAContract.View, FragmentTabAContract.Model> {


    public FragmentAPresenter(FragmentTabAContract.View mView, FragmentTabAContract.Model model) {
        super(mView, model);
    }

    @Override
    public void loadBanner() {
        addSubscribe(model.getBanners().subscribe(new Subscriber<List<FrontpageBean.DataBeanX.DataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.setBanner(null);
            }

            @Override
            public void onNext(List<FrontpageBean.DataBeanX.DataBean> dataBeen) {
                mView.setBanner(dataBeen);
                loadFeeds("T1348647909107", 0);
            }
        }));
    }

    @Override
    public void loadFeeds(String id, final int page) {
        model.getFeeds(id, page).subscribe(new Subscriber<List<NewsSummary>>() {
            @Override
            public void onCompleted() {
                setAllIsDone(true);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<NewsSummary> newsSummaries) {
                mView.setFeeds(newsSummaries);
                if (page == 0 && newsSummaries.size() == 0) {
                    mView.setProgress(false, null, true);
                } else {
                    mView.setProgress(false, null, false);
                }
            }
        });
    }

    @Override
    public void attatchView() {
        mView.setProgress(true, null, false);
    }

}

