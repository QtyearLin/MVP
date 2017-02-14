package core.image;

import android.widget.ImageView;

import core.R;


/**
 * Created by Lin on 2016/1/20.
 */

public class ImageConfig {

    protected ImageView imageView;
    protected String url;
    protected int placeholder;
    protected int errorPic;

    public ImageConfig() {
        this.placeholder = R.drawable.ic_place_holder;
        this.errorPic = R.drawable.ic_load_err;
    }

    public ImageConfig setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public ImageConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public ImageConfig setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public ImageConfig setErrorPic(int errorPic) {
        this.errorPic = errorPic;
        return this;
    }

    public String getUrl() {
        return url;
    }


    public ImageView getImageView() {
        return imageView;
    }


    public int getPlaceholder() {
        return placeholder;
    }


    public int getErrorPic() {
        return errorPic;
    }
}
