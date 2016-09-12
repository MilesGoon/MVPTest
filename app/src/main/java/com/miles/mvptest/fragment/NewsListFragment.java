package com.miles.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miles.mvptest.R;
import com.miles.mvptest.adapter.NewsAdapter;
import com.miles.mvptest.config.Constants;
import com.miles.mvptest.interfaces.NewsListViewInterface;
import com.miles.mvptest.presenter.NewsPresenter;
import com.miles.mvptest.utils.UIUtils;

public class NewsListFragment extends MVPBaseFragment<NewsListViewInterface, NewsPresenter> implements NewsListViewInterface {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String type;

    public static MVPBaseFragment newInstance() {
        return new NewsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_recycler_view, null, false);
            initViews();

            int position = getArguments().getInt("position");
            type = Constants.NEWS[position][0];
            mPresenter.fetchNews(type);
        }

        return mRootView;
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchNews(type);
            }
        });
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }

    @Override
    public void setAdapter(NewsAdapter newsAdapter) {
        mRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
