package core.image;

import android.content.Context;
import android.support.v4.app.Fragment;


/**
 * Created by Lin on 2016/1/20.
 */

public interface ImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context context, T t);
    void loadImage(Fragment fragment, T t);
}
