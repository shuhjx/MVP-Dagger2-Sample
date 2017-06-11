package com.shuh.osplearning.base;

import android.app.Application;
/**
 * Created by pc-135 on 2017/6/6.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;
    private ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        application = this;
    }

    public static BaseApplication getApplication() {
        return application;
    }

    private void initImageLoader(){
        imageLoader = new GlideImageLoader();
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }

}
