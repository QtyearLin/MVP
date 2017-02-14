package core.image.glide;

import core.image.ImageConfig;

/**
 * Created by Lin on 2016/1/20.
 */

public class GlideImageConfig extends ImageConfig {
    private int cacheStrategy;
    private boolean centerCrop;
    private boolean crossFade;

    public GlideImageConfig() {
        super();
        this.crossFade = true;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public boolean isCenterCrop() {
        return centerCrop;
    }

    public boolean isCrossFade() {
        return crossFade;
    }

    public GlideImageConfig setCenterCrop(boolean centerCrop) {
        this.centerCrop = centerCrop;
        return this;
    }

    public GlideImageConfig setCrossFade(boolean crossFade) {
        this.crossFade = crossFade;
        return this;
    }

    public GlideImageConfig setCacheStrategy(int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        return this;
    }

}
