package core.image.glide;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import core.image.ImageLoaderStrategy;

/**
 * Created by Lin on 2016/1/20.
 */

public class GlideImageLoaderStrategy implements ImageLoaderStrategy<GlideImageConfig> {

    @Override
    public void loadImage(Context context, GlideImageConfig imageConfig) {
        RequestManager manager;
        if (context instanceof Activity) {
            manager = Glide.with((Activity) context);
        } else {
            manager = Glide.with(context);
        }
        load(imageConfig, manager);
    }

    public DrawableRequestBuilder loadImageWithBuilder(Context context, GlideImageConfig imageConfig) {
        RequestManager manager;
        if (context instanceof Activity) {
            manager = Glide.with((Activity) context);
        } else {
            manager = Glide.with(context);
        }
        return load(imageConfig, manager);
    }

    private DrawableRequestBuilder<String> load(GlideImageConfig imageConfig, RequestManager manager) {
        DrawableRequestBuilder<String> request = manager.load(imageConfig.getUrl());
        if (imageConfig.isCenterCrop())
            request.centerCrop();
        if (imageConfig.isCrossFade())
            request.crossFade();
        switch (imageConfig.getCacheStrategy()) {
            case 0:
                request.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                request.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                request.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                break;
            case 3:
                request.diskCacheStrategy(DiskCacheStrategy.RESULT);
                break;
            default:
                break;
        }

        if (imageConfig.getPlaceholder() != 0) {
            request.placeholder(imageConfig.getPlaceholder());
        }
        if (imageConfig.getErrorPic() != 0) {
            request.error(imageConfig.getErrorPic());
        }
        request.into(imageConfig.getImageView());
        return request;
    }

    @Override
    public void loadImage(Fragment fragment, GlideImageConfig glideImageConfig) {
        RequestManager manager = Glide.with(fragment);
        load(glideImageConfig, manager);
    }
}
