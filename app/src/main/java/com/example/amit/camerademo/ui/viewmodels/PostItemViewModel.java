package com.example.amit.camerademo.ui.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.example.amit.camerademo.BR;
import com.example.amit.camerademo.api.models.PostModel;

import java.util.ArrayList;

public class PostItemViewModel extends BaseObservable {
    private PostModel dataModel;

    public PostItemViewModel(PostModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners

    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public String getTitle() {
        return !TextUtils.isEmpty(dataModel.getTitle()) ? dataModel.getTitle() : "";
    }
}
