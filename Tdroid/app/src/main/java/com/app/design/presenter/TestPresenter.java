package com.app.design.presenter;


import com.app.design.Contact.TestContract;

import core.base.BasePresenter;

/**
 * Created by Lin on 16/5/26.
 */
public class TestPresenter extends TestContract.Presenter{


    public TestPresenter(TestContract.View view, TestContract.Model model) {
        super(view,model);
    }

    @Override
    public void test() {

    }

    @Override
    public void attatchView() {
        mView.setProgress(true,null,false);
    }

}

