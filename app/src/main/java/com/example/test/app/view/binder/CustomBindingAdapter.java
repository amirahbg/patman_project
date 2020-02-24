package com.example.test.app.view.binder;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        if (imageUrl == null)
            return;

        Picasso.get()
                .load(imageUrl)
                .into(imageView);
    }
}
