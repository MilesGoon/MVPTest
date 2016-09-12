package com.miles.mvptest.interfaces;


import com.miles.mvptest.adapter.WeChatArticleAdapter;

public interface WeChatViewInterface {

    void showLoading();

    void hideLoading();

    void showError(String errMsg);

    void setAdapter(WeChatArticleAdapter weChatArticleAdapter);
}
