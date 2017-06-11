package com.shuh.osplearning.base;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by wyq_j on 2017/6/10.
 */
@GlideModule
public class CustomAppGlideModule extends AppGlideModule {

    public CustomAppGlideModule() {
        super();
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
//        return super.isManifestParsingEnabled();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
    }
}
