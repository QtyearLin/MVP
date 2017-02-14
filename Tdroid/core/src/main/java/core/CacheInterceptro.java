package core;

import android.content.Context;

import java.io.IOException;

import core.utils.BaseUtils;
import core.utils.LogUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lin on 2017/1/20.
 */
public class CacheInterceptro implements Interceptor {
    private static final String TAG = "CacheInterceptro";
    private static final long MAX_STALE = 60 * 60 * 24 * 28;
    private Context sContext;

    public CacheInterceptro(Context sContext) {
        this.sContext = sContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!BaseUtils.isNetworkReachable(sContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        if (BaseUtils.isNetworkReachable(sContext)) {
            String cacheControl = request.cacheControl().toString();
            LogUtils.i(TAG, "has network ,cacheControl=" + cacheControl);
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            long maxStale = MAX_STALE; // tolerate 4-weeks stale
            LogUtils.i(TAG, "network error ,maxStale=" + maxStale);
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }

    }
}
