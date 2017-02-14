package com.app.design.model;

import com.app.design.Contact.FragmentMainContract;
import com.app.design.Contact.FragmentTabAContract;
import com.app.design.app.AppApiService;
import com.app.design.bean.FrontpageBean;
import com.app.design.bean.GirlData;
import com.app.design.bean.GithubTest;
import com.app.design.bean.NewsSummary;
import com.app.design.bean.PhotoGirl;

import java.util.List;
import java.util.Map;

import core.base.Constant;
import core.baserx.RxSchedulers;
import core.utils.HostUtils;
import core.utils.RetrofitUtils;
import core.utils.TimeUtil;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public class FragmentTabAModel implements FragmentTabAContract.Model {
    public static FragmentTabAModel getInstance() {
        return FragmentTabAModel.PhotosListModelHolder.sInstance;
    }


    @Override
    public Observable<List<NewsSummary>> getFeeds(final String id, int page) {
        return RetrofitUtils.getInstance().getApiService(AppApiService.class).getNewsList("headline", id, page)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
                        return Observable.from(map.get(id));
                    }
                })
                //转化时间
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());


    }

    @Override
    public Observable<List<FrontpageBean.DataBeanX.DataBean>> getBanners() {
        return RetrofitUtils.getInstance(HostUtils.HOST_TYEP_DONGTING).getApiService(AppApiService.class).getFrontpage()
                .flatMap(new Func1<FrontpageBean, Observable<FrontpageBean.DataBeanX>>() {

                    @Override
                    public Observable<FrontpageBean.DataBeanX> call(FrontpageBean frontpageBean) {
                        return Observable.from(frontpageBean.getData());
                    }
                }).map(new Func1<FrontpageBean.DataBeanX, List<FrontpageBean.DataBeanX.DataBean>>() {
                    @Override
                    public List<FrontpageBean.DataBeanX.DataBean> call(FrontpageBean.DataBeanX dataBeanX) {
                        return dataBeanX.getData();
                    }
                }).first().compose(RxSchedulers.<List<FrontpageBean.DataBeanX.DataBean>>io_main());
    }
/*
    public void printStudentNames (List<Strudent> strudents) {
        Observable.from(strudents).map(new Func1<Strudent, String>() {
            @Override
            public String call(Strudent strudent) {
                return strudent.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtils.d(s);
            }
        });
    }

    */

    /**
     * flatMap 使用传入的事件对象创建一个Observable对象 并不是发送这个Observable对象 而是激活 然后该被观察者发送事件 每个创建出来的O白色女案例被发送的事件都被汇入同一个Observavle
     * 而这个Observable 负责将这些事件统一交给Subscriber而回调方法 三个步骤吧事件拆成了两级 通过一组新创建的Obserable 将初始的对象铺平之后通过统一路径分发了下去
     * 而这个铺平就是所谓的flatMap 中的flat
     *
     * @param strudents
     *//*

    public void printStudentCourses (List<Strudent> strudents) {
        Observable.from(strudents).flatMap(new Func1<Strudent, Observable<Course>>() {
            @Override
            public Observable<Course> call(Strudent strudent) {
                return strudent.getCourses();
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                LogUtils.d(course.getname());
            }
        });
    }
*/


    private static class PhotosListModelHolder {
        private static final FragmentTabAModel sInstance = new FragmentTabAModel();
    }
}
