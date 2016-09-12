package com.miles.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.miles.mvptest.R;
import com.miles.mvptest.adapter.JokeAdapter;
import com.miles.mvptest.interfaces.JokeViewInterface;
import com.miles.mvptest.presenter.JokePresenter;

public class JokeFragment extends MVPBaseFragment<JokeViewInterface, JokePresenter> implements JokeViewInterface {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar mProgressBar;
    boolean isLoading;
    FloatingActionButton mFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_joke, null);
            initViews();
            mPresenter.fetchJokes();
        }
        return mRootView;
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setProgressViewOffset(true, 20, 100);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);
        mFloatingActionButton = (FloatingActionButton) mRootView.findViewById(R.id.floatingActionButton);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchJokes();
            }
        });

        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected JokePresenter createPresenter() {
        return new JokePresenter();
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
        mProgressBar.setVisibility(View.GONE);
        isLoading = false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingActionButton) {
            mRecyclerView.getLayoutManager().scrollToPosition(0);
            mPresenter.fetchJokes();
        }
    }

    @Override
    public void setAdapter(JokeAdapter jokeAdapter) {
        mRecyclerView.setAdapter(jokeAdapter);
    }
}
