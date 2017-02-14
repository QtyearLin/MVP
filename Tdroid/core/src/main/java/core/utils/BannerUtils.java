package core.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import core.R;
import core.base.BaseBannerImageLoader;

/**
 * Created by Administrator on 2017/2/11.
 */
public class BannerUtils {
    private static final float BANNER_HEIGHT = 300;

    public static Banner init(Context context, List<String> images, List<String> titles, int styleWithTitle) {
        Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.layout_banner, null);
        banner.setImageLoader(new BaseBannerImageLoader());
        banner.setBannerStyle(styleWithTitle);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(images);
        banner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                BaseUtils.dip2px(context,BANNER_HEIGHT)));
        return banner;
    }

    public static Banner init(Context context, List<String> images) {
        Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.layout_banner, null);
        banner.setImageLoader(new BaseBannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(images);
        banner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                BaseUtils.dip2px(context, BANNER_HEIGHT)));
        return banner;
    }
}
