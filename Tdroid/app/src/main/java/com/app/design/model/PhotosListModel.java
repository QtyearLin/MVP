package com.app.design.model;

import com.app.design.Contact.FragmentMainContract;
import com.app.design.app.AppApiService;
import com.app.design.bean.GirlData;
import com.app.design.bean.GithubTest;
import com.app.design.bean.NewsSummary;
import com.app.design.bean.PhotoGirl;

import java.util.List;
import java.util.Map;

import core.base.Constant;
import core.baserx.RxSchedulers;
import core.utils.LogUtils;
import core.utils.RetrofitUtils;
import core.utils.TimeUtil;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public class PhotosListModel implements FragmentMainContract.Model {
    public static PhotosListModel getInstance() {
        return PhotosListModel.PhotosListModelHolder.sInstance;
    }

    public Observable<List<PhotoGirl>> getPhotosListData(int size, int page) {
        return  RetrofitUtils.getInstance().getApiService(AppApiService.class).getPhotoList(Constant.CACHE_CONTROL_AGE,size, page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                }).compose(RxSchedulers.<List<PhotoGirl>>io_main());
    }

    public Observable<GithubTest> getGitHubTestData() {
        return RetrofitUtils.getInstance().getApiService(AppApiService.class).getGitHubTestData()
                .compose(RxSchedulers.<GithubTest>io_main());
    }

    public Observable<List<NewsSummary>> getNewsList(final String type, final String id, final int startPage) {
        return RetrofitUtils.getInstance().getApiService(AppApiService.class).getNewsList(type, id, startPage)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
                        if (id.endsWith(Constant.HOUSE_ID)) {
                            // 房产实际上针对地区的它的id与返回key不同
                            return Observable.from(map.get("北京"));
                        }
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
        private static final PhotosListModel sInstance = new PhotosListModel();
    }
}
