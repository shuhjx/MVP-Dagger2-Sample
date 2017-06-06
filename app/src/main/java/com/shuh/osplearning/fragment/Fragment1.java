package com.shuh.osplearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuh.osplearning.R;
import com.shuh.osplearning.adapter.HomeAdapter;
import com.shuh.osplearning.base.BaseFragment;
import com.shuh.osplearning.utils.ViewUtils;

import java.util.ArrayList;

/**
 * Created by pc-135 on 2017/5/11.
 */
public class Fragment1 extends BaseFragment {

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ArrayList<String> list;

    @Override
    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, null);
        recyclerView = ViewUtils.getInstance().findViewById(view, R.id.recyclerView);

        return view;
    }

    @Override
    public void initViews(@Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i=0;i<30;i++)
            list.add("aaaaa"+i);
        adapter = new HomeAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }
}
