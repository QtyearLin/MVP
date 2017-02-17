package com.app.design.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.app.design.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/2/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FragmentRightSlideTest {

   /* @Rule是JUnit4的新特性。利用@Rule我们可以扩展JUnit的功能，在执行case的时候加入测试者特有的操作，而不影响原有的case代码：减小了特有操作和case原逻辑的耦合。譬如说我们要重复测试某个test方法，当然我们可以在@Test方法里面写循环。
    但是如果想把循环和测试逻辑分开就可以利用@Rule。
    我们先实现org.junit.rules.MethodRule接口做实循环逻辑，然后把实例放在testcase里面即可：*/
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

}