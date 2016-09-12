package com.miles.mvptest.presenter;


import android.content.Intent;

import com.miles.mvptest.WebActivity;
import com.miles.mvptest.adapter.WeChatArticleAdapter;
import com.miles.mvptest.base.BasePresenter;
import com.miles.mvptest.entites.WeChatArticle;
import com.miles.mvptest.interfaces.WeChatViewInterface;
import com.miles.mvptest.interfaces.listener.DataListener;
import com.miles.mvptest.interfaces.listener.OnItemClickListener;
import com.miles.mvptest.utils.UIUtils;

import java.util.List;

public class WeChatPresenter extends BasePresenter<WeChatViewInterface> {
    private WeChatArticleAdapter mWeChatArticleAdapter;
    private boolean isFirst = true;

    public void fetchArticles(int page) {
        getView().showLoading();
        mServerApi.fetchWeChatArticles(page, new DataListener<List<WeChatArticle>>() {
            @Override
            public void onComplete(List<WeChatArticle> result) {
                getView().hideLoading();
                if (result != null) {
                    if (isFirst){
                        isFirst = false;
                        mWeChatArticleAdapter = new WeChatArticleAdapter(result);
                        getView().setAdapter(mWeChatArticleAdapter);
                        mWeChatArticleAdapter.setOnItemClickListener(new OnItemClickListener<WeChatArticle>() {
                            @Override
                            public void onClick(WeChatArticle item) {
                                Intent intent = new Intent(UIUtils.getContext(), WebActivity.class);
                                intent.putExtra("url", item.url);
                                intent.putExtra("title", "微信精选");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                UIUtils.getContext().startActivity(intent);
                            }
                        });
                        return;
                    }

                    mWeChatArticleAdapter.setWeChatArticles(result);
                    mWeChatArticleAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
