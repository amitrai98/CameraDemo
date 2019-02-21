package com.example.amit.camerademo.ui.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.amit.camerademo.api.models.PostModel;
import com.example.amit.camerademo.ui.adapters.PostAdapter;

import java.util.List;

public class RecyclerViewDataBinding {
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, PostAdapter adapter, List<PostModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
}
