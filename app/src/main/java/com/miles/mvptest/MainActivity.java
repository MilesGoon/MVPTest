package com.miles.mvptest;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miles.mvptest.base.BaseMVPActivity;
import com.miles.mvptest.interfaces.MainViewInterface;
import com.miles.mvptest.presenter.MainPresenter;
import com.miles.mvptest.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseMVPActivity<MainViewInterface, MainPresenter> implements MainViewInterface {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.left_list)
    ListView mLeftList;
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void init() {
        mToolbar.setTitle("新闻客户端");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentManager = getSupportFragmentManager();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLeftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 切换内容fragment
                mPresenter.switchFragment(position);
                mDrawerLayout.closeDrawers();
            }
        });
        mPresenter.switchFragment(0);
    }

    @Override
    public void switchFragment(Fragment newFragment, String title) {
        if (newFragment != null) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            hideOtherFragment(title);
            if (newFragment.isAdded()) {
                mFragmentTransaction.show(newFragment);
            } else {
                mFragmentTransaction.add(R.id.frame_content, newFragment, title);
                mFragmentTransaction.addToBackStack(title);
            }
            mToolbar.setTitle(title);
            mFragmentTransaction.commit();
        }
    }

    private void hideOtherFragment(String title) {
        String[] titles = UIUtils.getStringArr(R.array.left_item);
        for (String string : titles) {
            if (!TextUtils.equals(string, title)) {
                Fragment fragment = mFragmentManager.findFragmentByTag(string);
                if (fragment != null) {
                    mFragmentTransaction.hide(fragment);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 2000) {
            finish();
        } else {
            lastTime = System.currentTimeMillis();
            // 类似Toast
            Snackbar.make(mLeftList, "双击退出程序", Snackbar.LENGTH_SHORT).show();
        }
    }
}
