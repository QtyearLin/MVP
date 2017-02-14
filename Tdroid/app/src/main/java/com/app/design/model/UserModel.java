package com.app.design.model;

/**
 * Created by Qtyearlin on 2017/2/4.
 */
public class UserModel {
    public static UserModel getInstance() {
        return UserModelHolder.sInstance;
    }

    private UserModel() {

    }

    private static class UserModelHolder {
        private static final UserModel sInstance = new UserModel();
    }
}
