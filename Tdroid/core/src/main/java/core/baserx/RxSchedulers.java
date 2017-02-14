package core.baserx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lin on 2017/2/4.
 */
public class RxSchedulers {
    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
  /*  3) compose: 对 Observable 整体的变换

    除了 lift() 之外， Observable 还有一个变换方法叫做 compose(Transformer)。它和 lift() 的区别在于， lift() 是针对事件项和事件序列的，而 compose() 是针对 Observable 自身进行变换。举个例子，假设在程序中有多个 Observable ，并且他们都需要应用一组相同的 lift() 变换。你可以这么写：

            observable1
                    .lift1()
            .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber1);
    observable2
            .lift1()
            .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber2);
    observable3
            .lift1()
            .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber3);
    observable4
            .lift1()
            .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber1);
    你觉得这样太不软件工程了，于是你改成了这样：

    private Observable liftAll(Observable observable) {
        return observable
                .lift1()
                .lift2()
                .lift3()
                .lift4();
    }
    ...
    liftAll(observable1).subscribe(subscriber1);
    liftAll(observable2).subscribe(subscriber2);
    liftAll(observable3).subscribe(subscriber3);
    liftAll(observable4).subscribe(subscriber4);
    可读性、可维护性都提高了。可是 Observable 被一个方法包起来，这种方式对于 Observale 的灵活性似乎还是增添了那么点限制。怎么办？这个时候，就应该用 compose() 来解决了：

    public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
        @Override
        public Observable<String> call(Observable<Integer> observable) {
            return observable
                    .lift1()
                    .lift2()
                    .lift3()
                    .lift4();
        }
    }
    ...
    Transformer liftAll = new LiftAllTransformer();
    observable1.compose(liftAll).subscribe(subscriber1);
    observable2.compose(liftAll).subscribe(subscriber2);
    observable3.compose(liftAll).subscribe(subscriber3);
    observable4.compose(liftAll).subscribe(subscriber4);
    像上面这样，使用 compose() 方法，Observable 可以利用传入的 Transformer 对象的 call 方法直接对自身进行处理，也就不必被包在方法的里面了。

    compose() 的原理比较简单，不附图喽。*/
}
