package core.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import core.R;


/**
 * Created by Lin on 2016/6/8.
 */
public class FragmentUtils {

    /**
     * 添加fragment
     *
     * @param id
     * @param tag
     * @param fragmentManager
     * @param addToStack
     * @param fragmentTransaction
     */
    public static void addFragmentByTag(int id, Class<? extends Fragment> z, FragmentManager fragmentManager, boolean
            addToStack,
                                        FragmentTransaction fragmentTransaction) {
        if (null == z || null == fragmentManager)
            return;
        if (null == fragmentTransaction)
            fragmentTransaction = fragmentManager.beginTransaction();
        String tag = z.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (null == fragment) {
            try {
                fragment = z.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (null == fragment)
            return;
        if (!fragment.isAdded()) {
            if (addToStack) {
                fragmentTransaction.add(id, fragment, tag).addToBackStack(tag).show(fragment).commitAllowingStateLoss();
            } else {
                fragmentTransaction.add(id, fragment, tag).show(fragment).commitAllowingStateLoss();
            }
            fragmentManager.executePendingTransactions();
        } else {
            fragmentTransaction.show(fragment).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    /**
     * 添加fragment
     *
     * @param id
     * @param fragment
     * @param fragmentManager
     * @param addToStack
     * @param fragmentTransaction
     */
    public static void addFragment(int id, Fragment fragment, FragmentManager fragmentManager, boolean addToStack,
                                   FragmentTransaction fragmentTransaction) {
        if (null == fragment || null == fragmentManager)
            return;
        if (null == fragmentTransaction)
            fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            String tag = fragment.getClass().getSimpleName();
           /* Fragment oldFragment = fragmentManager.findFragmentByTag(tag);
            if (null!=oldFragment){
                fragment = oldFragment;
            }*/
            if (addToStack) {
                fragmentTransaction.add(id, fragment, tag).addToBackStack(tag).show(fragment).commitAllowingStateLoss();
            } else {
                fragmentTransaction.add(id, fragment, tag).show(fragment).commitAllowingStateLoss();
            }
            fragmentManager.executePendingTransactions();
        } else {
            fragmentTransaction.show(fragment).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    /**
     * replacefragment
     *
     * @param fragment
     * @param id
     * @param fragmentManager
     * @param addToStack
     * @param fragmentTransaction
     */
    public static void replaceFragment(Fragment fragment, int id, FragmentManager fragmentManager, boolean
            addToStack, FragmentTransaction fragmentTransaction) {
        if (null == fragment || null == fragmentTransaction)
            return;
        String tag = fragment.getClass().getSimpleName();
        if (addToStack) {
            fragmentTransaction.replace(id, fragment, tag).addToBackStack(tag).show(fragment).commitAllowingStateLoss();
        } else {
            fragmentTransaction.replace(id, fragment, tag).show(fragment).commitAllowingStateLoss();
        }
        fragmentManager.executePendingTransactions();
    }

    /**
     * fragment切换
     *
     * @param from
     * @param to
     * @param fragmentManager
     * @param fragmentTransaction
     */
    public static void showFragment(Fragment from, Fragment to, FragmentManager fragmentManager, FragmentTransaction
            fragmentTransaction) {
        if (null == from || to == null || !to.isAdded() || fragmentManager == null)
            return;
        if (null == fragmentTransaction)
            fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(from).show(to).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    /**
     * fragment切换
     *
     * @param id
     * @param from
     * @param to
     * @param fragmentManager
     * @param fragmentTransaction
     */
    public static void showFragment(int id, Fragment from, Fragment to, FragmentManager fragmentManager, boolean
            addToStack, FragmentTransaction fragmentTransaction) {
        if (null == from || to == null || fragmentManager == null)
            return;
        if (null == fragmentTransaction)
            fragmentTransaction = fragmentManager.beginTransaction();
        if (!to.isAdded()) {
            String tag = to.getClass().getSimpleName();
            if (addToStack) {
                fragmentTransaction.add(id, to, tag).addToBackStack(tag).hide(from).show(to).commitAllowingStateLoss();
            } else {
                fragmentTransaction.add(id, to, tag).hide(from).show(to).commitAllowingStateLoss();
            }
        } else {
            fragmentTransaction.hide(from).show(to).commitAllowingStateLoss();
        }
        fragmentManager.executePendingTransactions();
    }

    /**
     * fragment切换
     *
     * @param from
     * @param to
     * @param fragmentManager
     * @param fragmentTransaction
     */
    public static void showFragmentWithReplace(Fragment from, Fragment to, FragmentManager fragmentManager,
                                               FragmentTransaction fragmentTransaction) {
        if (null == from || to == null || !to.isAdded() || fragmentManager == null)
            return;
        if (null == fragmentTransaction)
            fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(from).show(to).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();

    }

    public static FragmentTransaction getFragmentTransaction(FragmentManager fragmentManager, int animalIn, int
            animalOut) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(animalIn,
                animalOut);
        return fragmentTransaction;
    }

    public static FragmentTransaction getCustomDefaultFragmentTransaction(FragmentManager fragmentManager, boolean isBack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!isBack) {
            fragmentTransaction.setCustomAnimations(R.anim.anim_right_in_3s,
                    R.anim.anim_left_out_3s);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.anim_left_in_3s,
                    R.anim.anim_right_out_3s);
        }
        return fragmentTransaction;
    }

    public static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
