package core.image;

import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * Created by Lin on 2016/1/20.
 */

public class ImageLoader {

    private ImageLoaderStrategy mImageLoaderStrategy;

    private ImageLoader() {
    }

    public ImageLoaderStrategy getImageLoaderStrategy() {
        return mImageLoaderStrategy;
    }

    public void setImageLoaderStragety(ImageLoaderStrategy imageLoaderStrategy) {
        this.mImageLoaderStrategy = imageLoaderStrategy;
    }


    public <T extends ImageConfig> void displayImage(Context context, T config) {
        this.mImageLoaderStrategy.loadImage(context, config);
    }

    public <T extends ImageConfig> void displayImage(Fragment fragment, T config) {
        this.mImageLoaderStrategy.loadImage(fragment, config);
    }


    public static ImageLoader getInstance() {
        return ImageLoaderInstance.mImageLoader;
    }


    public static class ImageLoaderInstance {
        private final static ImageLoader mImageLoader = new ImageLoader();
    }

}
