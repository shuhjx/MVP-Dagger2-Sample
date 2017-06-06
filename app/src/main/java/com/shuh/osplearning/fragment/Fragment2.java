package com.shuh.osplearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuh.osplearning.R;
import com.shuh.osplearning.base.BaseFragment;

/**
 * Created by pc-135 on 2017/5/11.
 */
public class Fragment2 extends BaseFragment {

    @Override
    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f2, null);
    }

    @Override
    public void initViews(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }
}
