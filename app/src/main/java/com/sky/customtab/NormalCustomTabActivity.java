package com.sky.customtab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sky.customtabview.OnTabItemSelectedListener;
import sky.customtabview.TabNormalItem;
import sky.customtabview.TabView;

/**
 * description:
 * created by sky
 * 17/10/30 上午11:02
 */
public class NormalCustomTabActivity extends AppCompatActivity {

    private static int DEFAULT_INDEX = 1;

    public static void startNormalCustomTabActivity(Activity activity) {
        Intent intent = new Intent(activity, NormalCustomTabActivity.class);
        activity.startActivity(intent);
    }

    private ViewPager mViewPager;
    private List<View> mViews = new ArrayList<>();
    private TabView mTabView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_customtab);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        View view1 = new View(this);
        view1.setLayoutParams(layoutParams);
        view1.setBackgroundColor(Color.WHITE);
        mViews.add(view1);

        View view2 = new View(this);
        view2.setLayoutParams(layoutParams);
        view2.setBackgroundColor(Color.GRAY);
        mViews.add(view2);

        View view3 = new View(this);
        view3.setLayoutParams(layoutParams);
        view3.setBackgroundColor(Color.RED);
        mViews.add(view3);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ViewPagerAdapter());
        mViewPager.setCurrentItem(DEFAULT_INDEX, false);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabView.setSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabView = (TabView) findViewById(R.id.tabview);
        mTabView.init()
                .addTabItem(newItem(R.drawable.accessory, R.drawable.accessory_checked, "accessory"))
                .addTabItem(newItem(R.drawable.activity, R.drawable.activity_checked, "activity"))
                .addTabItem(newItem(R.drawable.addpeople, R.drawable.addpeople_checked, "addpeople"))
                .setDefaultIndex(DEFAULT_INDEX)
                .setOnTabItemSelectedListener(new OnTabItemSelectedListener() {
                    @Override
                    public void onSelected(int index) {
                        mViewPager.setCurrentItem(index, false);
                    }
                })
                .build();

        mTabView.setHasMessage(1, true);
    }

    private TabNormalItem newItem(int drawableRes, int checkedDrawableRes, String title) {
        TabNormalItem tabNormalItem = new TabNormalItem(this);
        tabNormalItem.setDrawableRes(drawableRes, checkedDrawableRes);
        tabNormalItem.setText(title);
        tabNormalItem.setTextColor(Color.parseColor("#dbdbdb"), Color.parseColor("#13227a"));
        return tabNormalItem;
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }
    }
}
