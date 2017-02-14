package core.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import core.CacheInterceptro;
import core.HeadInterceptro;
import core.LoggerInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Qtyearlin on 2016/2/4.
 */
public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";
    private static final long CONNECTION_TIMEOUT = 10;
    private static final long WRITE_TIMEOUT = 60;
    private static final long READ_TIMEOUT = 60;


    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;

    public synchronized static OkHttpUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new OkHttpUtils(context);
        }
        return mInstance;
    }


    private OkHttpUtils(Context context) {
        init(context);
    }


    private void init(@NonNull Context mAppContext) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mOkHttpClient = builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(CacheUtil.getOkhttpCache(mAppContext))
                .addInterceptor(new LoggerInterceptor(true))
//                .addInterceptor(logInterceptor)
                .addInterceptor(new HeadInterceptro(mAppContext))
                .addNetworkInterceptor(new CacheInterceptro(mAppContext)).build();
    }


    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


}
