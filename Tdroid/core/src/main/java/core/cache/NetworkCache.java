package core.cache;

import rx.Observable;

/**
 * Created by Tyearlin on 16/12/10.
 */
public abstract class NetworkCache<T> {
    public abstract Observable<T> get(String key, final Class<T> cls);
}
