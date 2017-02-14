package com.app.design.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.design.R;
import com.app.design.app.AppApplication;
import com.app.design.bean.NewsSummary;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import core.utils.DisplayUtils;
import core.view.CoreViewHolder;

/**
 * Created by Administrator on 2017/2/12.
 */

public class NewsAdapterItemPhoto implements NewsAdapter.MultipleSubPerformer<NewsSummary,CoreViewHolder> {


    @Override
    public void convertByType(CoreViewHolder holder, NewsSummary newsSummary) {
        String title = newsSummary.getTitle();
        String ptime = newsSummary.getPtime();
        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        setImageView(holder, newsSummary);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_photo;
    }

    @Override
    public int getType() {
        return NewsAdapter.ITEM_TYPE_PHOTO;
    }

    private void setImageView(CoreViewHolder holder, NewsSummary newsSummary) {
        int PhotoThreeHeight = DisplayUtils.dip2px(90);
        int PhotoTwoHeight = DisplayUtils.dip2px(120);
        int PhotoOneHeight = DisplayUtils.dip2px(150);

        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;
        LinearLayout news_summary_photo_iv_group = holder.getView(R.id.news_summary_photo_iv_group);
        ViewGroup.LayoutParams layoutParams = news_summary_photo_iv_group.getLayoutParams();

        if (newsSummary.getAds() != null) {
            List<NewsSummary.AdsBean> adsBeanList = newsSummary.getAds();
            int size = adsBeanList.size();
            if (size >= 3) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();
                layoutParams.height = PhotoThreeHeight;
                holder.setText(R.id.news_summary_title_tv, AppApplication.getAppContext()
                        .getString(R.string.photo_collections, adsBeanList.get(0).getTitle()));
            } else if (size >= 2) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else if (newsSummary.getImgextra() != null) {
            int size = newsSummary.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsSummary.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else {
            imgSrcLeft = newsSummary.getImgsrc();

            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        news_summary_photo_iv_group.setLayoutParams(layoutParams);
    }

    private void setPhotoImageView(CoreViewHolder holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight) {
        if (imgSrcLeft != null) {
            holder.setVisible(R.id.news_summary_photo_iv_left, true);
            holder.setImageUrl(R.id.news_summary_photo_iv_left, imgSrcLeft);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_left, false);
        }
        if (imgSrcMiddle != null) {
            holder.setVisible(R.id.news_summary_photo_iv_middle, true);
            holder.setImageUrl(R.id.news_summary_photo_iv_middle, imgSrcMiddle);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_middle, false);
        }
        if (imgSrcRight != null) {
            holder.setVisible(R.id.news_summary_photo_iv_right, true);
            holder.setImageUrl(R.id.news_summary_photo_iv_right, imgSrcRight);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_right, false);
        }
    }
}
