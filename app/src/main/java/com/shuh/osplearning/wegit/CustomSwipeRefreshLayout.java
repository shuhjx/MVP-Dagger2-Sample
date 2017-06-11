package com.shuh.osplearning.wegit;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wyq_j on 2017/6/10.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private OnLoadListener onLoadListener;
    private boolean isLoading = false;

    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener = onLoadListener;
    }

    public interface OnLoadListener{
        void onLoad();
    }

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 判断容器有多少个孩子
        if (getChildCount() > 0) {
            // RecyclerView
            if (getChildAt(0) instanceof RecyclerView) {
                // 创建RecyclerView对象
                recyclerView = (RecyclerView) getChildAt(0);
                layoutManager = recyclerView.getLayoutManager();
                // 设置RecyclerView的滑动监听
                setRecyclerViewOnScroll();
            }
        }
    }

    private void setRecyclerViewOnScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount()) {
                    if (CustomSwipeRefreshLayout.this.isRefreshing()) {
                        recyclerView.getAdapter().notifyItemRemoved(recyclerView.getAdapter().getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        loadData();
                    }
                }
            }
        });
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        isLoading = false;
    }

    private void loadData(){
        if(onLoadListener != null)
            onLoadListener.onLoad();
    }
}
