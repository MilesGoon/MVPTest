package com.miles.mvptest.interfaces;


import com.miles.mvptest.adapter.NewsAdapter;

public interface NewsListViewInterface {

    void showLoading();

    void hideLoading();

    void setAdapter(NewsAdapter newsAdapter);
}
