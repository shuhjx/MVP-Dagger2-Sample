package com.shuh.osplearning;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shuh.osplearning.adapter.MainViewPagerAdapter;
import com.shuh.osplearning.base.BaseActivity;
import com.shuh.osplearning.fragment.Fragment1;
import com.shuh.osplearning.fragment.Fragment2;
import com.shuh.osplearning.fragment.Fragment3;
import com.shuh.osplearning.fragment.Fragment4;
import com.shuh.osplearning.utils.ViewUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewPagerAdapter adapter;
    private ArrayList<Fragment> fragments;

    protected void findViews(){
        setContentView(R.layout.activity_main);
        viewPager = ViewUtils.getInstance().findViewById(this, R.id.viewPager);
        tabLayout = ViewUtils.getInstance().findViewById(this, R.id.tabLayout);
    }

    protected void initViews(){
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        adapter.setTitles(new String[]{"首页", "发现", "进货单","我的"});
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        tabLayout.addTab(tabLayout.newTab(), true);
        tabLayout.addTab(tabLayout.newTab(), false);
        tabLayout.addTab(tabLayout.newTab(), false);
        tabLayout.addTab(tabLayout.newTab(), false);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    protected void setListener(){

    }
}
