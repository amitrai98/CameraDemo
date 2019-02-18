package com.example.amit.camerademo.api.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageUploadModel implements Parcelable {
    String imagePath;

    public ImageUploadModel(String imagePath){
        this.imagePath = imagePath;
    }

    protected ImageUploadModel(Parcel in) {
        imagePath = in.readString();
    }

    public static final Creator<ImageUploadModel> CREATOR = new Creator<ImageUploadModel>() {
        @Override
        public ImageUploadModel createFromParcel(Parcel in) {
            return new ImageUploadModel(in);
        }

        @Override
        public ImageUploadModel[] newArray(int size) {
            return new ImageUploadModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
    }
}
