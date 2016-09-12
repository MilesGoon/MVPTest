package com.miles.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miles.mvptest.R;
import com.miles.mvptest.adapter.WeChatArticleAdapter;
import com.miles.mvptest.interfaces.WeChatViewInterface;
import com.miles.mvptest.presenter.WeChatPresenter;

public class WeChatArticleFragment extends MVPBaseFragment<WeChatViewInterface, WeChatPresenter> implements WeChatViewInterface {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static int page = 1;

    @Override
    protected WeChatPresenter createPresenter() {
        return new WeChatPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_recycler_view, null);
            initViews();
            mPresenter.fetchArticles(page);
        }
        return mRootView;
    }

    public void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.fetchArticles(page);
            }
        });
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }

    @Override
    public void showError(String errMsg) {
        Snackbar.make(mRecyclerView, errMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(WeChatArticleAdapter weChatArticleAdapter) {
        mRecyclerView.setAdapter(weChatArticleAdapter);
    }

}
