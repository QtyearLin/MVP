package com.app.design.app;

import com.app.design.bean.FrontpageBean;
import com.app.design.bean.GirlData;
import com.app.design.bean.GithubTest;
import com.app.design.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Qtyearlin on 2017/2/6.
 */

public interface AppApiService {
    /**
     * retrofit 风格 无 rxjava
     *
     * @Path 路径 @Query 请求参数
     */
    /*    @GET("user/login" )
        Call<User> login(
                @Query("username") String username,
                @Query("password") String password
        );

      Call<UserInfo> call = RetrofitUtils.getInstance().getApiService().login("1111", "ssss");
      call.enqueue(new Callback<UserInfo>() {
          @Override
          public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
              //请求成功操作
          }
          @Override
          public void onFailure(Call<UserInfo> call, Throwable t) {
              //请求失败操作
          }
      });

    /**
     * rxjava 风格
     * @param username
     * @param password
     * @return
     */
  /*  @GET("login")
    Observable<BaseResponseBody<User>>
    login(@Query("username") String username, @Query("password") String password);
    service.login(phone, password)               //获取Observable对象
            .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
            .observeOn(Schedulers.io())         //请求完成后在io线程中执行
            .doOnNext(new Action1<UserInfo>() {
        @Override
        public void call(UserInfo userInfo) {
            saveUserInfo(userInfo);//保存用户信息到本地
        }
    })
            .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
            .subscribe(new Subscriber<UserInfo>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            //请求失败
        }

        @Override
        public void onNext(UserInfo userInfo) {
            //请求成功
        }
    });*/

  /*  @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Header("Cache-Control") String cacheControl,
            @Url String photoPath);
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);

    @GET("nc/video/list/{type}/n/{startPage}-10.html")
    Observable<Map<String, List<VideoData>>> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("startPage") int startPage);*/
    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);
    @GET()
    Observable<GithubTest> getGitHubTestData();
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);


    /**
     * 首页轮播图
     */
    @GET("/frontpage/frontpage")
    Observable<FrontpageBean> getFrontpage();

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     *//*
    @GET("/data/{type}/{pre_page}/{page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);*/

   /* *//**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2015/08/06
     *//*
    @GET("/day/{year}/{month}/{day}")
    Observable<GankIoDayBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);*/

   /* *//**
     * 豆瓣热映电影，每日更新
     *//*
    @GET("/v2/movie/in_theaters")
    Observable<HotMovieBean> getHotMovie();
*/
   /* *//**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     *//*
    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);

    *//**
     * 获取豆瓣电影top250
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     *//*
    @GET("/v2/movie/top250")
    Observable<HotMovieBean> getMovieTop250(@Query("start") int start, @Query("count") int count);
*/
   /* *//**
     * 根据tag获取图书
     *
     * @param tag   搜索关键字
     * @param count 一次请求的数目 最多100
     *//*

    @GET("/v2/book/search")
    Observable<BookBean> getBook(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);*/

   /* @GET("/v2/book/{id}")
    Observable<BookDetailBean> getBookDetail(@Path("id") String id);*/
}
