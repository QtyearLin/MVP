package core.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import core.callback.DiffObserver;

/**
 * Created by Tyearlin on 16/12/10.
 */
public abstract class BaseMultipleDispatchItemAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends
        BaseMultiItemQuickAdapter<T,
                K> {

    private SparseArray<MultipleSubPerformer> map = new SparseArray<>();

    public BaseMultipleDispatchItemAdapter(List data) {
        super(data);
        bindPerformers();
    }

    private void bindPerformers() {
        List<MultipleSubPerformer<T, K>> performers = createPerformersFactors();
        if (null != performers) {
            for (MultipleSubPerformer p : performers) {
                map.put(p.getType(), p);
                addItemType(p.getType(), p.getLayoutId());
            }
        }
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(new DiffObserver(observer,getHeaderLayoutCount()));
//        super.registerAdapterDataObserver(observer);
    }

    @Override
    protected K createBaseViewHolder(View view) {
        Class<K> z = (Class<K>) ((ParameterizedType) (getClass()
                .getGenericSuperclass())).getActualTypeArguments()[1];
        Constructor constructor;
        try {
            String buffer = Modifier.toString(z.getModifiers());
            String className = z.getName();
            if (className.contains("$") && !buffer.contains("static")) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                return (K) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                return (K) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (K) new BaseViewHolder(view);
    }

    @Override
    protected void convert(K helper, T item) {
        int type = helper.getItemViewType();
        MultipleSubPerformer adapter = map.get(type);
        adapter.convertByType(helper, item);

    }

    public interface MultipleSubPerformer<T extends MultiItemEntity, K extends BaseViewHolder> {

        void convertByType(K holder, T multiItemEntity);

        int getLayoutId();

        int getType();

    }

    protected abstract List<MultipleSubPerformer<T, K>> createPerformersFactors();
}
