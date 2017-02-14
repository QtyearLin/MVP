package core;

import android.content.Context;
import android.os.Build;

import java.io.IOException;

import core.utils.BaseUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lin on 2017/1/20.
 */
public class HeadInterceptro implements Interceptor {

    private Context appContext;

    public HeadInterceptro(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request build = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
//                .addHeader("Client-Agent", getRequestHead())
                .build();

        return chain.proceed(build);
    }

    private String getRequestHead() {
        String temp = "device:" + String.valueOf(new Build().MODEL) + ";os:android" + Build.VERSION.SDK + ";version:"
                + String.valueOf(BaseUtils.getVersionCode(appContext));
        return temp;
    }
}
