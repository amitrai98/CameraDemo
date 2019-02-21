package com.example.amit.camerademo.ui.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.amit.camerademo.BR;
import com.example.amit.camerademo.R;
import com.example.amit.camerademo.api.models.PostModel;
import com.example.amit.camerademo.databinding.PostItemBinding;
import com.example.amit.camerademo.ui.activities.HomeScreen;
import com.example.amit.camerademo.ui.fragments.CameraFragment;
import com.example.amit.camerademo.ui.viewmodels.CameraViewModel;
import com.example.amit.camerademo.ui.viewmodels.PostItemViewModel;
import com.example.amit.camerademo.ui.viewmodels.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.DataViewHolder> {
    private static final String TAG = "PostAdapter";
    private List<PostModel> data;
    static HomeScreen homeScreen;


    public PostAdapter(HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
        this.data = new ArrayList<>();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        PostModel dataModel = data.get(position);
        holder.setViewModel(new PostItemViewModel(dataModel));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<PostModel> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
       PostItemBinding binding;

       DataViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
                binding.postImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeScreen.changeFragment(new CameraFragment(), CameraFragment.TAG);
                    }
                });
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        void setViewModel(PostItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewmodel(viewModel);
            }
        }
    }
}
