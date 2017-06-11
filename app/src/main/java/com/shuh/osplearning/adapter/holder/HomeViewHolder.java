package com.shuh.osplearning.adapter.holder;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.shuh.osplearning.R;
import com.shuh.osplearning.base.BaseRecyclerAdapter;
import com.shuh.osplearning.fragment.Fragment1;
import com.shuh.osplearning.model.impl.HomeModel;

/**
 * Created by pc-135 on 2017/6/7.
 */
public class HomeViewHolder extends BaseRecyclerAdapter.BaseViewHolder {

    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public <M extends BaseRecyclerAdapter.BaseModel> void setData(M model) {
        HomeModel m = (HomeModel) model;
        setText(R.id.text, m.getTitle())
                .setImage(R.id.image, m.getImgurl());
    }
}
