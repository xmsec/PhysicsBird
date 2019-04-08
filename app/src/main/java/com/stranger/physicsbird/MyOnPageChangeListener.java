package com.stranger.physicsbird;

import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by stranger on 2016/1/30.
 */
public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
    int two = one * 2;// 页卡1 -> 页卡3 偏移量

    public void onPageScrollStateChanged(int arg0) {


    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {


    }

    public void onPageSelected(int arg0) {
            /*两种方法，这个是一种，下面还有一种，显然这个比较麻烦
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;

            }
            */
        Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);//显然这个比较简洁，只有一行代码。
        currIndex = arg0;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        //  imageView.startAnimation(animation);
        // Toast.makeText(MainActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
    }

}