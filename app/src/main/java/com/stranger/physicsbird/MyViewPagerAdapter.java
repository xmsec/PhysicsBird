package com.stranger.physicsbird;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stranger on 2016/1/30.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> mListViews;

    public MyViewPagerAdapter(List<View> mListViews) {
        this.mListViews = mListViews;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListViews.get(position));
        return mListViews.get(position);
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "均值计算";
            case 1:return "剔除坏值";
            case 2:return "不确定度";
            case 3:return "线性回归";
            default:return "";
        }
    }
}
