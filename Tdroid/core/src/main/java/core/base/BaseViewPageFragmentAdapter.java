package core.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseViewPageFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mFragmentTitles;


    public BaseViewPageFragmentAdapter(FragmentManager fm, String[] mFragmentTitles, Fragment... mFragments) {
        super(fm);
        this.mFragments = Arrays.asList(mFragments);
        this.mFragmentTitles = mFragmentTitles;
    }

    public BaseViewPageFragmentAdapter(FragmentManager fm, String[] mFragmentTitles, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
        this.mFragmentTitles = mFragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    //刷新fragment
    public void setFragments(FragmentManager fm, List<Fragment> fragments, String[] mTitles) {
        this.mFragmentTitles = mTitles;
        if (this.mFragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.mFragments) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            ft = null;
            fm.executePendingTransactions();
        }
        this.mFragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != mFragmentTitles && mFragmentTitles.length > position)
            return mFragmentTitles[position];
        return "";

    }

}