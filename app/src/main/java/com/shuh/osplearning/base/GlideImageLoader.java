package com.shuh.osplearning.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shuh.osplearning.R;
import com.shuh.osplearning.utils.ScreenUtils;

/**
 * Created by wyq_j on 2017/6/10.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void setImageUrl(Context context, String url, final ImageView imageView){
        if(url == null || imageView == null) return;
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = ScreenUtils.calHeight(resource.getWidth(), resource.getHeight());
                        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        imageView.setLayoutParams(layoutParams);
                    }
                });
    }

    @Override
    public void setImageUrl(View view, String url, final ImageView imageView) {
        if(url == null || imageView == null) return;
        GlideApp.with(view)
                .asBitmap()
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = ScreenUtils.calHeight(resource.getWidth(), resource.getHeight());
                        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        imageView.setLayoutParams(layoutParams);
                    }
                });
    }
}
