package com.app.design.ui;

/**
 * Created by Administrator on 2017/2/15.
 */

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.app.design.MainActivity;
import com.app.design.R;
import com.app.design.app.AppDb;
import com.app.design.bean.User;
import com.app.design.databinding.ActivityMainBinding;
import com.greendao.gen.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "auto_test_now";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void quit() {
        MainActivity activity = mActivityRule.getActivity();
        activity.onBackPressed();
        activity.onBackPressed();
        onView(withId(R.id.fab)).check(matches(isDisplayed()));


    }

    @Test
    public void viewSimpleDone() {
        //输入测试
        onView(ViewMatchers.withId(R.id.test_edt)).perform(typeText(STRING_TO_BE_TYPED),
                closeSoftKeyboard()); //line 1
        //点击测试
        onView(withText("testButton")).perform(click()); //line 2
        //更新测试
        String expectedText = "testButton";
        onView(withId(R.id.test_btn)).check(matches(withText(expectedText))); //line 3*/
    }

}