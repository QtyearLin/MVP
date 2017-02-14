package core.baserx;

/**
 * Created by jingbin on 16/5/17.
 */
public class RxBusBaseMessage<T> {
    private int code;
    private T object;
    public RxBusBaseMessage(int code, T object){
        this.code=code;
        this.object=object;
    }

    public int getCode() {
        return code;
    }

    public T getObject() {
        return object;
    }
}
