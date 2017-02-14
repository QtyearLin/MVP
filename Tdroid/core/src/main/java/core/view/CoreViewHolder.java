package core.view;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/2/12.
 */

// TODO: 2017/2/13 BaseViewHolder  泛型错误  默认创建K
//  @see method  BaseQuickAdapter
/*protected K createBaseViewHolder(View view) {
        return new BaseViewHolder(view);
        }
        */
public class CoreViewHolder extends BaseViewHolder {
    public CoreViewHolder(View view) {
        super(view);
    }

    public BaseViewHolder setImageUrl(int viewId, String url) {
        ImageView view = (ImageView) this.getView(viewId);
        view.setImageURI(Uri.parse(url));
        return this;
    }
}
