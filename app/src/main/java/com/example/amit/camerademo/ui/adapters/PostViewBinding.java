package com.example.amit.camerademo.ui.adapters;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.api.models.PostModel;
import com.example.amit.camerademo.ui.commonui.BaseViewModel;
import com.squareup.picasso.Picasso;

public class PostViewBinding extends BaseObservable {
    private PostModel dataModel;

    public PostViewBinding(PostModel dataModel) {
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
