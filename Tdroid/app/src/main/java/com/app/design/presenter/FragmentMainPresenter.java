package com.app.design.presenter;



import com.app.design.Contact.FragmentMainContract;

/**
 * Created by Lin on 16/5/26.
 */
public class FragmentMainPresenter extends FragmentMainContract.Presenter {


    public FragmentMainPresenter(FragmentMainContract.View view, FragmentMainContract.Model model) {
        super(view, model);
    }


    @Override
    public void attatchView() {
        mView.setProgress(true, null, false);
        /*model.getPhotosListData(10, 1).subscribe(new Subscriber<List<PhotoGirl>>() {
            @Override
            public void onCompleted() {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onError(Throwable e) {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onNext(List<PhotoGirl> photoGirls) {
                mView.setProgress(false, null, false);
                mView.setPhotosListData(photoGirls);
            }
        });
        model.getGitHubTestData().subscribe(new Subscriber<GithubTest>() {
            @Override
            public void onCompleted() {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onError(Throwable e) {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onNext(GithubTest githubTest) {
                mView.setProgress(false, null, false);
            }
        });*/
        /*addSubscribe(model.getNewsList("headline", "T1348647909107", 0).subscribe(new Subscriber<List<NewsSummary>>() {
            @Override
            public void onCompleted() {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onError(Throwable e) {
                mView.setProgress(false, null, false);
            }

            @Override
            public void onNext(List<NewsSummary> newsSummaries) {
                mView.setProgress(false, null, false);
            }
        }));*/
    }


}

