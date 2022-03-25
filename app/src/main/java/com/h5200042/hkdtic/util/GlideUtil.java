package com.h5200042.hkdtic.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class GlideUtil {
    public static void downloadimgandshow(Context context, String imgUrl, ImageView whichImageView) {
        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .into(whichImageView);


    }
}
