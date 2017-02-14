package core;

import android.text.TextUtils;

import java.io.IOException;

import core.utils.LogUtils;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Lin on 2016/12/8.
 */

public class LoggerInterceptor implements Interceptor {
    private boolean showResponse;

    public LoggerInterceptor(boolean showResponse) {
        this.showResponse = showResponse;
    }


    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response) {
        try {
            //===>response log
            LogUtils.d("========response'log=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            LogUtils.d("url : " + clone.request().url());
            LogUtils.d("code : " + clone.code());
            LogUtils.d("protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
                LogUtils.d("message : " + clone.message());

            if (showResponse) {
                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        LogUtils.d("responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                            LogUtils.d("responseBody's content : " + resp);

                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            LogUtils.d("responseBody's content : " + " maybe [file part] , too large too print ," +
                                    " " +
                                    "ignored!");
                        }
                    }
                }
            }

            LogUtils.d("========response'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            LogUtils.d("========request'log=======");
            LogUtils.d("method : " + request.method());
            LogUtils.d("url : " + url);
            if (headers != null && headers.size() > 0) {
                LogUtils.d("headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    LogUtils.d("requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        LogUtils.d("requestBody's content : " + bodyToString(request));
                    } else {
                        LogUtils.d("requestBody's content : " + " maybe [file part] , too large too print , " +
                                "ignored!");
                    }
                }
            }
            LogUtils.d("========request'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
