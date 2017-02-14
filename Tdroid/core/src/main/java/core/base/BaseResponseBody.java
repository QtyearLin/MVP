package core.base;

/**
 * Created by Lin on 2017/1/20.
 */

public class BaseResponseBody<T> {
    public String msg;
    public int code;
    public T response;

    public BaseResponseBody(String msg, int code, T response) {
        this.msg = msg;
        this.code = code;
        this.response = response;
    }

}
