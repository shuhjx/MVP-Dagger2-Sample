package com.shuh.osplearning.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
//import android.util.SparseArray;
import android.view.View;

/**
 * Created by pc-135 on 2017/6/5.
 */
public class ViewUtils {

    private ViewUtils(){}

    private static ViewUtils instance;

//    private SparseArray<View> views = new SparseArray();

    public static ViewUtils getInstance(){
        if(instance == null){
            synchronized (ViewUtils.class){
                if(instance == null){
                    instance = new ViewUtils();
                }
            }
        }
        return instance;
    }

    public <T extends View>T findViewById(View view, @IdRes int id){
        if (view == null) throw new RuntimeException("ViewUtils#findViewById params error, view must not null!");
//        View v = views.get(id);
//        if(v != null) return (T) v;
//        v = view.findViewById(id);
//        views.put(id, v);
//        return (T) v;
        return (T) view.findViewById(id);
    }

    public <T extends View>T findViewById(Activity activity, @IdRes int id){
        if(activity == null) throw new RuntimeException("ViewUtils#findViewById params error, activity must not null!");
//        View v = views.get(id);
//        if(v != null) return (T) v;
//        v = activity.findViewById(id);
//        views.put(id, v);
//        return (T) v;
        return (T) activity.findViewById(id);
    }
}
