package com.shuh.osplearning.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by pc-135 on 2017/5/11.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private ArrayList<Fragment> fragments;

    public void setTitles(String[] titles){
        this.titles = titles;
    }

    public void setFragments(ArrayList<Fragment> fragments){
        this.fragments = fragments;
    }

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        try{
            return fragments.get(position);
        }catch (Exception e){
            return null;
        }
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        try{
            return titles[position];
        }catch (Exception e){
            return null;
        }
    }
}
