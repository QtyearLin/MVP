package com.app.design.adapter;

import android.widget.ImageView;

import com.app.design.R;
import com.app.design.bean.NewsSummary;

import core.base.BaseMultipleDispatchItemAdapter;
import core.image.ImageLoader;
import core.image.glide.GlideImageConfig;
import core.view.CoreViewHolder;

/**
 * Created by Administrator on 2017/2/12.
 */

public class NewsAdapterItem implements BaseMultipleDispatchItemAdapter.MultipleSubPerformer<NewsSummary, CoreViewHolder> {


    @Override
    public void convertByType(CoreViewHolder holder, NewsSummary newsSummary) {
        String title = newsSummary.getLtitle();
        if (title == null) {
            title = newsSummary.getTitle();
        }
        String ptime = newsSummary.getPtime();
        String digest = newsSummary.getDigest();
        String imgSrc = newsSummary.getImgsrc();

        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        holder.setText(R.id.news_summary_digest_tv, digest);
        ImageView imageView = holder.getView(R.id.news_summary_photo_iv);
        ImageLoader.getInstance().displayImage(imageView.getContext(), new GlideImageConfig()
                .setImageView(imageView)
                .setUrl(imgSrc));
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news;
    }

    @Override
    public int getType() {
        return NewsAdapter.ITEM_TYPE_NEWS;
    }


}
