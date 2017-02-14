package core;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lin on 2017/1/20.
 */

public class OAuthInterceptor implements Interceptor {

    private final String username;
    private final String password;

    public OAuthInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String credentials = username + ":" + password;

        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Request originalRequest = chain.request();
        String cacheControl = originalRequest.cacheControl().toString();

        Request.Builder requestBuilder = originalRequest.newBuilder()
                //Basic Authentication,也可用于token验证,OAuth验证
                .header("Authorization", basic)
                .header("Accept", "application/json")
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();

        Response originalResponse = chain.proceed(request);
        Response.Builder responseBuilder =
                //Cache control设置缓存
                originalResponse.newBuilder().header("Cache-Control", cacheControl);

        return responseBuilder.build();
    }
}
