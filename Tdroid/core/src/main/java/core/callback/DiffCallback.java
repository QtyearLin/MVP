package core.callback;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public class DiffCallback<T> extends DiffUtil.Callback {
    protected List<T> mOldList = new ArrayList<>();
    protected List<T> mNewList = new ArrayList<>();

    public DiffCallback(List<T> mOldList, List<T> mNewList) {
        this.mOldList.clear();
        this.mNewList.clear();
        this.mOldList.addAll(mOldList);
        this.mNewList.addAll(mNewList);
    }

    @Override
    public int getOldListSize() {
        return null == mOldList ? 0 : mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return null == mNewList ? 0 : mNewList.size();
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * For example, if your items have unique ids, this method should check their id equality.
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     * 本例判断name字段是否一致
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldT = mOldList.get(oldItemPosition);
        T newT = mNewList.get(newItemPosition);
        return oldT.equals(newT);
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     * DiffUtil uses this method to check equality instead of {@link Object#equals(Object)}
     * DiffUtil 用这个方法替代equals方法去检查是否相等。
     * so that you can change its behavior depending on your UI.
     * 所以你可以根据你的UI去改变它的返回值
     * For example, if you are using DiffUtil with PtrRefreshListenner
     * {@link android.support.v7.widget.RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     * This method is called only if {@link #areItemsTheSame(int, int)} returns
     * {@code true} for these items.
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     *                        oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldT = mOldList.get(oldItemPosition);
        T newT = mNewList.get(newItemPosition);
        return oldT.equals(newT);
    }

    /**
     * 返回新老数据改变的item数据对象 bundle 实现局部刷新
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
