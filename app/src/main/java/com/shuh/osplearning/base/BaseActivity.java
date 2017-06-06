package com.shuh.osplearning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by pc-135 on 2017/6/5.
 */
public abstract class BaseActivity extends AppCompatActivity implements ViewListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews(null, null, savedInstanceState);
        initViews(savedInstanceState);
        setListener();
    }
}
