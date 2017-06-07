package com.shuh.osplearning.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc-135 on 2017/6/7.
 */
public abstract class BaseRecyclerAdapter<H extends BaseRecyclerAdapter.BaseViewHolder> extends RecyclerView.Adapter<H> {

    protected ArrayList<? extends BaseModel> list;
    protected Context context;

    public BaseRecyclerAdapter(Context context, ArrayList<? extends BaseModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    abstract public H createHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        protected View parent;
        protected SparseArray<View> views = new SparseArray<>();

        public BaseViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
        }

        public abstract <M extends BaseModel> void setData(M model);

        public View findView(@IdRes int id){
            View v = views.get(id);
            if(v == null){
                v = parent.findViewById(id);
                views.put(id, v);
            }
            return v;
        }

        public TextView getTextView(@IdRes int id){
            return (TextView) findView(id);
        }
        public View setText(@IdRes int id, CharSequence text){
            getTextView(id).setText(text);
            return parent;
        }

        public ImageView getImageView(@IdRes int id){
            return (ImageView) findView(id);
        }


    }

    public static class BaseModel {
    }
}
