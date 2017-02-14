package com.app.design.model;

import com.app.design.Contact.TestContract;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public class TestModel implements TestContract.Model {
    public static TestModel getInstance() {
        return TestModelHolder.sInstance;
    }

    private TestModel() {
    }

    @Override
    public String test() {
        return "test";
    }

    private static class TestModelHolder {
        private static final TestModel sInstance = new TestModel();
    }
}
