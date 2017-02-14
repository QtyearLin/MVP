package core.base;

/**
 * Created by Tyearlin on 16/12/10.
 */
public interface IView {
    void setProgress(boolean show, String text, boolean isEmpty);

    void showErrorTip(String msg);
}
