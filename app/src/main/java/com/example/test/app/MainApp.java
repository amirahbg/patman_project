package com.example.test.app;

import android.app.Application;

import com.example.test.app.di.AndroidModule;
import com.example.test.app.di.AppComponent;
import com.example.test.app.di.DaggerAppComponent;

public class MainApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
