package core.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Tyearlin on 16/12/10.
 */
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V, M> {

    protected V mView;
    protected M model;
    protected CompositeSubscription mCompositeSubscription;
    private boolean allIsDone = false;


    public BasePresenter(V mView, M model) {
        this.mView = mView;
        this.model = model;
        attatchView();
    }


    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public boolean isAllIsDone() {
        return allIsDone;
    }

    public void setAllIsDone(boolean allIsDone) {
        this.allIsDone = allIsDone;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
