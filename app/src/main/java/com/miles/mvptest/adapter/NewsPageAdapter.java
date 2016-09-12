package com.miles.mvptest.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miles.mvptest.config.Constants;
import com.miles.mvptest.fragment.MVPBaseFragment;
import com.miles.mvptest.fragment.NewsListFragment;

import java.util.HashMap;
import java.util.Map;

public class NewsPageAdapter extends FragmentPagerAdapter {
    private Map<Integer, MVPBaseFragment> mFragmentMaps = new HashMap<>();

    public NewsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return Constants.NEWS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.NEWS[position][1];
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private MVPBaseFragment getFragment(int position) {
        MVPBaseFragment mvpBaseFragment = mFragmentMaps.get(position);
        if (mvpBaseFragment == null) {
            mvpBaseFragment = NewsListFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            mvpBaseFragment.setArguments(bundle);
        }

        return mvpBaseFragment;
    }
}
