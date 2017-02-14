package core.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * 只适用于静态参数 fragment
 *
 */
@Deprecated
public class BaseViewPageTagFragmentAdapter extends FragmentPagerAdapter {

    private Class<Fragment>[] mFragmentTags;
    private String[] mFragmentTitles;
    private FragmentManager mFragmentManager;


    public BaseViewPageTagFragmentAdapter(FragmentManager fragmentManager, Class<Fragment>[] mFragmentTags, String[]
            mFragmentTitles) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mFragmentTags = mFragmentTags;
        this.mFragmentTitles = mFragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Class tagClass = mFragmentTags[position];
        Fragment fragment = mFragmentManager.findFragmentByTag(tagClass.getSimpleName());
        if (null != fragment)
            return fragment;
        try {
            return (Fragment) tagClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragmentTags.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null == mFragmentTitles || mFragmentTags.length < position)
            return null;
        return mFragmentTitles[position];
    }

}