package core.base;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import core.image.glide.GlideImageConfig;

/**
 * Created by Administrator on 2017/2/11.
 */

public class BaseBannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法
//        Glide.with(context).load(path).into(imageView);
        core.image.ImageLoader.getInstance().
                displayImage(context, new GlideImageConfig().setCrossFade(true).setImageView(imageView).setUrl(path
                        .toString()));

        //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView)

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
    }
}
//