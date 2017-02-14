package core.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import core.image.ImageLoader;
import core.image.glide.GlideImageLoaderStrategy;
import core.utils.RetrofitUtils;

/**
 * BaseApplication
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        initImageLoader();
        RetrofitUtils.setAppContext(this);
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    /**
     * imageloader
     */
    private void initImageLoader() {
        ImageLoader.getInstance().setImageLoaderStragety(new GlideImageLoaderStrategy());
    }

}
