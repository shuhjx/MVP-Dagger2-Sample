package com.shuh.osplearning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pc-135 on 2017/6/6.
 */
public interface ViewListener {

    View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    void initViews(@Nullable Bundle savedInstanceState);
    void setListener();
}
