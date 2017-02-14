package com.app.design.adapter;

import android.os.Bundle;
import android.widget.ImageView;

import com.app.design.R;
import com.app.design.bean.NewsSummary;

import java.util.ArrayList;
import java.util.List;

import core.base.BaseMultipleDispatchItemAdapter;
import core.base.Constant;
import core.image.ImageLoader;
import core.image.glide.GlideImageConfig;
import core.view.CoreViewHolder;

/**
 * Created by Administrator on 2017/2/12.
 */

public class NewsAdapter extends BaseMultipleDispatchItemAdapter<NewsSummary, CoreViewHolder> {
    public static final int ITEM_TYPE_NEWS = 0;
    public static final int ITEM_TYPE_PHOTO = 1;

    public NewsAdapter(List<NewsSummary> data) {
        super(data);
    }

    @Override
    public void onBindViewHolder(CoreViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            for (String key : payload.keySet()) {
                switch (key) {
                    case Constant.KEY.TITLE:
                        holder.setText(R.id.news_summary_title_tv, payload.getString(key));
                        break;
                    case Constant.KEY.PICURL:
                        ImageView imageview = holder.getView(R.id.news_summary_photo_iv);
                        ImageLoader.getInstance().displayImage(imageview.getContext(), new GlideImageConfig()
                                .setImageView(imageview)
                                .setUrl(payload.getString(key)));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected List<MultipleSubPerformer<NewsSummary, CoreViewHolder>> createPerformersFactors() {
        List<MultipleSubPerformer<NewsSummary, CoreViewHolder>> list = new ArrayList<>();
        list.add(new NewsAdapterItem());
        list.add(new NewsAdapterItemPhoto());
        return list;
    }
}
