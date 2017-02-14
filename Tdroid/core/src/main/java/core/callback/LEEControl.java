package core.callback;

/**
 * Created by Tyearlin on 2014/6/22
 */
public interface LEEControl {


    /**
     * @param emptyText
     */
    void showEmpty(String emptyText);

    /**
     *
     * @param text
     */
    void showError(String text);

    /**
     * @param text
     * @param isEmpty
     */
    void showLoading(String text, boolean isEmpty);

    /**
     *
     */
    void showSuccess();


}
