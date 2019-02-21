package com.example.amit.camerademo.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.databinding.FragmentCameraBinding;
import com.example.amit.camerademo.databinding.FragmentPostListingBinding;
import com.example.amit.camerademo.ui.activities.HomeScreen;
import com.example.amit.camerademo.ui.commonui.BaseFragment;
import com.example.amit.camerademo.ui.viewmodels.CameraViewModel;
import com.example.amit.camerademo.ui.viewmodels.PostViewModel;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostListing extends Fragment {

    public static String TAG = PostListing.class.getSimpleName();
    FragmentPostListingBinding binding;
    PostViewModel postViewModel;
    HomeScreen homeScreen = null;

    public PostListing() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() instanceof HomeScreen)
            homeScreen = (HomeScreen) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_post_listing, container, false);
        postViewModel = new PostViewModel(homeScreen);
        binding.setViewModel(postViewModel);
        initRecyclerView(binding.getRoot());
        return binding.getRoot();
    }



    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
    }

    @Override
    public void onResume() {
        super.onResume();
        postViewModel.setUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        postViewModel.tearDown();
    }
}
