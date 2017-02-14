package core.view;

/**
 * 上拉加载更多
 */
public abstract interface CoreFooterViewImp {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     * <p/>
     * 初始化footView
     */
    public abstract void onUIReset2();

    /**
     * prepare for loading
     * 加载前预处理
     */
    public abstract void onUIRefreshPrepare2();

    /**
     * perform refreshing UI
     */
    public abstract void onUIRefreshBegin2();

    /**
     * perform UI after refresh
     */
    public abstract void onUIRefreshComplete2();


}
