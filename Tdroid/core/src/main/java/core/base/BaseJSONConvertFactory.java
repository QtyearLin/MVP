package core.base;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Lin on 2017/1/20.
 */

public class BaseJSONConvertFactory<T> implements Converter<ResponseBody,BaseResponseBody<T>> {
    private Gson gson;

    public BaseJSONConvertFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public BaseResponseBody<T> convert(ResponseBody value) throws IOException {
        return null;
    }

  /*  @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.toString();
        try {
            LogUtils.d("Network", "response>>" + response);
            //ResultResponse 只解析result字段
            BaseResponseBody<T> resultResponse = gson.fromJson(response, BaseResponseBody.class);
            if (resultResponse.code == 0){
                return resultResponse.response;
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = gson.fromJson(response, ErrResponse.class);
                throw new ResultException(resultResponse.msg, errResponse.getMsg());
            }
        } finally {

        }
        return null;
    }*/
}
