package com.shuh.osplearning.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.shuh.osplearning.base.BaseApplication;

/**
 * Created by wyq_j on 2017/6/10.
 */

public class ScreenUtils {

    private static int screenWidth = -1;
    private static int screenHeight = -1;
    public static int getScreenWidth(){
        if(screenWidth == -1){
            Resources resources = BaseApplication.getApplication().getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
//            float density = dm.density;
            screenWidth = dm.widthPixels;
        }
        return screenWidth;
    }

    public static int getScreenHeight(){
        if(screenHeight == -1){
            Resources resources = BaseApplication.getApplication().getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
//            float density = dm.density;
            screenHeight = dm.heightPixels;
        }
        return screenHeight;
    }

    public static int calHeight(int width, int height){
        return (int) ((double) getScreenWidth() * height) / width;
    }
}
