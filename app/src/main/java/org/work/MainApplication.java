package org.work;

import android.app.Application;

public class MainApplication extends Application {

    private static MainApplication mInstance;

    public static MainApplication getInstance() {
        return  mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
