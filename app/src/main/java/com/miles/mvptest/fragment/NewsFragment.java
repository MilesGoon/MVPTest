package com.miles.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miles.mvptest.R;
import com.miles.mvptest.adapter.NewsPageAdapter;
import com.miles.mvptest.config.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment {
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_news_list, null, false);
            ButterKnife.bind(this, mRootView);
            initView();
        }

        return mRootView;
    }

    private void initView() {
        for (int i = 0; i < Constants.NEWS.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mViewpager.setAdapter(new NewsPageAdapter(getActivity().getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mAppBarLayout.setExpanded(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
