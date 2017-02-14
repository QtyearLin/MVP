package core.base;

/**
 * Created by Tyearlin on 16/12/10.
 */
public interface IPresenter<V extends IView,M extends IModel> {
    void attatchView();
    void detachView();
}
