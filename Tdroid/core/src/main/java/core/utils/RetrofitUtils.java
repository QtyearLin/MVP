package core.utils;

import android.content.Context;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.base.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static core.utils.HostUtils.HOST_TYEP_DEFAULT;

/**
 * Created by Lin on 2017/1/20.
 */

public class RetrofitUtils {

    private static final String HOST_DEFAULT = HostUtils.sHostMap.get(HOST_TYEP_DEFAULT);
    private static RetrofitUtils mInstance;
    private static Context mAppContext;
    private static SparseArray<RetrofitUtils> sRetrofitManager = new SparseArray<>(HostUtils.TYPE_COUNT);
    public Object mApiServier;
    private Retrofit mRetrofit;

    public synchronized static RetrofitUtils getInstance() {
        return getInstance(HOST_TYEP_DEFAULT);
    }

    public synchronized static RetrofitUtils getInstance(int HostType) {
        mInstance = sRetrofitManager.get(HostType);
        if (null == mInstance) {
            mInstance = new RetrofitUtils(HostType);
            sRetrofitManager.put(HostType, mInstance);
        }
        return mInstance;
    }


    public static void setAppContext(Context mAppContext) {
        RetrofitUtils.mAppContext = mAppContext;
    }

    private RetrofitUtils(int hostType) {
        init(hostType);
    }

    private void init(int hostType) {
       final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HostUtils.getHostByType(hostType))
                .client(OkHttpUtils.getInstance(mAppContext).getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T getApiService(Class<T> t) {
        if (null == mApiServier)
            mApiServier = mRetrofit.create(t);
        return (T) mApiServier;
    }
}
