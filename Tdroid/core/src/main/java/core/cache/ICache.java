package core.cache;

import rx.Observable;

/**
 * Created by Tyearlin on 16/12/10.
 */
public interface ICache {
    <T> Observable<T> get(String key, Class<T> cls);

    <T> void put(String key, T t);
}
