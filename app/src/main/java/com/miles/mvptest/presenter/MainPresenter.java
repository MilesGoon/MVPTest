package com.miles.mvptest.presenter;

import android.support.v4.app.Fragment;

import com.miles.mvptest.R;
import com.miles.mvptest.base.BasePresenter;
import com.miles.mvptest.fragment.JokeFragment;
import com.miles.mvptest.fragment.NewsFragment;
import com.miles.mvptest.fragment.WeChatArticleFragment;
import com.miles.mvptest.interfaces.MainViewInterface;
import com.miles.mvptest.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

public class MainPresenter extends BasePresenter<MainViewInterface> {
    private Map<Integer, Fragment> mFragments = new HashMap<>();

    public void switchFragment(int position) {

        Fragment newFragment = mFragments.get(position);
        if (newFragment == null) {
            switch (position) {
                case 0:
                    newFragment = new NewsFragment();
                    break;

                case 1:
                    newFragment = new JokeFragment();
                    break;

                case 2:
                    newFragment = new WeChatArticleFragment();
                    break;
            }
            mFragments.put(position, newFragment);
        }
        String[] array = UIUtils.getStringArr(R.array.left_item);
        String title = null;
        if (position < array.length) {
            title = array[position];
        }
        getView().switchFragment(newFragment, title);
    }
}
