package com.example.amit.camerademo.ui.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.amit.camerademo.BR;
import com.example.amit.camerademo.api.models.PostModel;
import com.example.amit.camerademo.ui.activities.HomeScreen;
import com.example.amit.camerademo.ui.adapters.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel  extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private PostAdapter adapter;
    private List<PostModel> data;


    public PostViewModel(HomeScreen homeScreen) {
        data = new ArrayList<>();
        adapter = new PostAdapter(homeScreen);
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        populateData();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<PostModel> getData() {
        return this.data;
    }

    @Bindable
    public PostAdapter getAdapter() {
        return this.adapter;
    }

    private void populateData() {
        // populate the data from the source, such as the database.
        for (int i = 0; i < 10; i++) {
            PostModel dataModel = new PostModel();
            dataModel.setTitle(String.valueOf(i));
            data.add(dataModel);
        }
        notifyPropertyChanged(BR.data);
    }
}
