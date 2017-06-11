package com.shuh.osplearning.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wyq_j on 2017/6/10.
 */

public interface ImageLoader {

    void setImageUrl(Context context, String url, ImageView imageView);
//    <F extends Fragment> void setImageUrl(F f, String url, ImageView imageView);
    void setImageUrl(View view, String url, ImageView imageView);
}
