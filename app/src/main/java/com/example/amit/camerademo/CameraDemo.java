package com.example.amit.camerademo;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.example.amit.camerademo.ui.databinding.AppDataBindingComponent;

public class CameraDemo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}
