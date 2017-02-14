package core.callback;

import android.view.View;


/**
 * Created by Tyearlin on 2014/6/22
 */
public interface EmptyViewControl extends LEEControl {

    /**
     * @param resId
     */
    void setEmptyView(int resId);

    /**
     * @param v
     */
    void setEmptyView(View v);

    /**
     * @param view
     */
    void bindView(View view);

    /**
     * @param listener
     */
    void setOnRefreshClick(View.OnClickListener listener);


}
