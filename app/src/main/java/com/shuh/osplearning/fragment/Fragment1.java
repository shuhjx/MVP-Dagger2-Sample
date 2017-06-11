package com.shuh.osplearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuh.osplearning.R;
import com.shuh.osplearning.adapter.holder.HomeViewHolder;
import com.shuh.osplearning.base.BaseFragment;
import com.shuh.osplearning.base.BaseRecyclerAdapter;
import com.shuh.osplearning.model.impl.HomeModel;
import com.shuh.osplearning.retrofit.services.HomeService;
import com.shuh.osplearning.retrofit.services.factory.HomeConverterFactory;
import com.shuh.osplearning.utils.ViewUtils;
import com.shuh.osplearning.wegit.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by pc-135 on 2017/5/11.
 */
public class Fragment1 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private CustomSwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private BaseRecyclerAdapter<HomeViewHolder> adapter;
    final private List<HomeModel> list = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private FloatingActionButton floatingBtn;
    private int currentPage = 1;
    private static final int LIMIT = 10;
    private static String TYPE = "lady";//"tech";

    @Override
    public View findViews(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, null);
        recyclerView = ViewUtils.getInstance().findViewById(view, R.id.recyclerView);
        swipeRefreshLayout = ViewUtils.getInstance().findViewById(view, R.id.swipeRefreshLayout);
        floatingBtn = ViewUtils.getInstance().findViewById(view, R.id.floatingBtn);
        return view;
    }

    @Override
    public void initViews(@Nullable Bundle savedInstanceState) {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        adapter = new BaseRecyclerAdapter<HomeViewHolder>(getActivity(), list){
            @Override
            public HomeViewHolder createHolder(ViewGroup parent, int viewType) {
                return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item, parent, false));
            }
        };

        mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        getDataFromHttp(currentPage);
    }

    @Override
    public void setListener() {
        swipeRefreshLayout.setOnLoadListener(new CustomSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                getDataFromHttp(currentPage);
            }
        });
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SweetAlertDialog dialog = new SweetAlertDialog(getActivity());
//                dialog.setCancelable(false);
////                dialog.setContentView(R.layout.news_type_dialog);
//
//                dialog.setTitleText("新闻类型")
//                        .setCancelText("取消")
//                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                sweetAlertDialog.dismissWithAnimation();
//                            }
//                        })
//                        .setConfirmText("确认")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                RadioGroup radioGroup = (RadioGroup) sweetAlertDialog.findViewById(R.id.radioGroup);
//                                int checked = radioGroup.getCheckedRadioButtonId();
//                                if(checked != -1){
//                                    View radio = sweetAlertDialog.findViewById(checked);
//                                    TYPE = (String) radio.getTag();
//                                    sweetAlertDialog.dismissWithAnimation();
//                                    getDataFromHttp(1);
//                                }
//                            }
//                        }).show();
            }
        });
    }

    private void getDataFromHttp(final int page){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(HomeConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HomeService.BASE_URL)
                .build();

        HomeService service = retrofit.create(HomeService.class);

        //subscribeOn()：指定subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
        //observeOn()：指定Subscriber 所运行在的线程。或者叫做事件消费的线程。
        //observeOn()可以多次使用，可以随意变换线程
        service.list(TYPE, page, LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeModel>>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<HomeModel> homeModels) {
                        if(page == 1) list.clear();
                        list.addAll(homeModels);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        currentPage ++;
                    }
                });
    }

    @Override
    public void onRefresh() {
        getDataFromHttp(1);
    }
}
