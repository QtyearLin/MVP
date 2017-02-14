package com.app.design.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.design.Contact.FragmentTabAContract;
import com.app.design.R;
import com.app.design.adapter.NewsAdapter;
import com.app.design.app.AppBaseFragment;
import com.app.design.bean.FrontpageBean;
import com.app.design.bean.NewsSummary;
import com.app.design.databinding.FragmentTabABinding;
import com.app.design.model.FragmentTabAModel;
import com.app.design.presenter.FragmentAPresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import core.base.Constant;
import core.callback.DiffCallback;
import core.callback.PtrRefreshListenner;
import core.utils.BannerUtils;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class FragmentA extends AppBaseFragment<FragmentTabABinding, FragmentAPresenter> implements
        FragmentTabAContract.View {


    private List<NewsSummary> mAdapterData = new ArrayList<>();
    private NewsAdapter mAdapter;
    private Banner mBanner;
    private List<String> mBannerImgList = new ArrayList<>();
    private boolean isRefresh = false;


    @Override
    protected void loadData() {
        mPresenter.loadBanner();
    }


    @Override
    protected FragmentAPresenter initPresenter() {
        return new FragmentAPresenter(this, FragmentTabAModel.getInstance());
    }

    @Override
    protected void setUpViews(Bundle savedInstanceState) {
        bindingView.ptr.setonRefreshListenner(new PtrRefreshListenner() {
            @Override
            public void onRefresh(PtrFrameLayout frame) {
                isRefresh = true;
                mPresenter.loadFeeds("T1348647909107", 0);
            }
        });
        final View headView = intHeadView(mBannerImgList);
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
        mAdapter = new NewsAdapter(mAdapterData);
        mAdapter.addHeaderView(headView);
        bindingView.recycleView.setAdapter(mAdapter);

    }

    private View intHeadView(List<String> images) {
        mBanner = BannerUtils.init(getContext(), images);
        return mBanner;
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void setBanner(List<FrontpageBean.DataBeanX.DataBean> list) {
        if (null != list) {
            Observable.from(list).map(new Func1<FrontpageBean.DataBeanX.DataBean, String>() {
                @Override
                public String call(FrontpageBean.DataBeanX.DataBean dataBean) {
                    return dataBean.getPicUrl();
                }
            }).buffer(list.size()).subscribe(new Action1<List<String>>() {
                @Override
                public void call(List<String> strings) {
                    mBanner.update(strings);
                }
            });
        }
    }

    @Override
    public void setFeeds(final List<NewsSummary> list) {
        //第二个参数代表是否检测Item的移动
        if (isRefresh) {
            diffAdapter(list.subList(0, 1));
        } else {
            diffAdapter(list);
        }
        //模拟刷新新数据
        if (isRefresh) {
            bindingView.ptr.refreshComplete();
            isRefresh = false;
        }
        isLoadFinished = true;
    }


    private void diffAdapter(final List<NewsSummary> list) {
        Observable.create(new Observable.OnSubscribe<DiffUtil.DiffResult>() {
            @Override
            public void call(Subscriber<? super DiffUtil.DiffResult> subscriber) {
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback<NewsSummary>(mAdapterData,
                        list) {
                    @Override
                    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                        NewsSummary oldT = mOldList.get(oldItemPosition);
                        NewsSummary newT = mNewList.get(newItemPosition);
                        return oldT.getOrder() == newT.getOrder();
                    }

                    @Override
                    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                        NewsSummary oldT = mOldList.get(oldItemPosition);
                        NewsSummary newT = mNewList.get(newItemPosition);
                        return (oldT.getImgsrc().equals(newT.getImgsrc()) && oldT.getTitle().equals(newT.getTitle()));
                    }

                    @Nullable
                    @Override
                    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                        NewsSummary oldT = mOldList.get(oldItemPosition);
                        NewsSummary newT = mNewList.get(newItemPosition);
                        Bundle payload = new Bundle();
                        if (!oldT.getTitle().equals(newT.getTitle())) {
                            payload.putString(Constant.KEY.TITLE, newT.getTitle());
                        }
                        if (oldT.getImgsrc() != oldT.getImgsrc()) {
                            payload.putString(Constant.KEY.PICURL, newT.getImgsrc());
                        }

                        if (payload.size() == 0)
                            return null;
                        return payload;
                    }
                }, true);
                subscriber.onNext(diffResult);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<DiffUtil.DiffResult>() {
            @Override
            public void call(DiffUtil.DiffResult diffResult) {
                mAdapterData.clear();
                mAdapterData.addAll(list);
                diffResult.dispatchUpdatesTo(mAdapter);
            }
        });
    }

    @Override
    public void setProgress(boolean show, String text, boolean isEmpty) {
        super.setProgress(show, text, isEmpty);
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.fragment_tab_a;
    }
}
