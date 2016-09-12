package com.miles.mvptest.presenter;


import android.content.Intent;

import com.miles.mvptest.WebActivity;
import com.miles.mvptest.adapter.NewsAdapter;
import com.miles.mvptest.base.BasePresenter;
import com.miles.mvptest.entites.News;
import com.miles.mvptest.interfaces.NewsListViewInterface;
import com.miles.mvptest.interfaces.listener.DataListener;
import com.miles.mvptest.interfaces.listener.OnItemClickListener;
import com.miles.mvptest.utils.UIUtils;

import java.util.List;

public class NewsPresenter extends BasePresenter<NewsListViewInterface> {
    private NewsAdapter mNewsAdapter;
    private boolean isFirst = true;


    public void fetchNews(String type) {
        getView().showLoading();
        mServerApi.fetchNews(type, new DataListener<List<News>>() {
            @Override
            public void onComplete(List<News> result) {
                getView().hideLoading();
                if (result != null) {
                    if (isFirst) {
                        isFirst = false;
                        mNewsAdapter = new NewsAdapter(result);
                        getView().setAdapter(mNewsAdapter);

                        mNewsAdapter.setOnItemClickListener(new OnItemClickListener<News>() {
                            @Override
                            public void onClick(News item) {
                                Intent intent = new Intent(UIUtils.getContext(), WebActivity.class);
                                intent.putExtra("url", item.url);
                                intent.putExtra("title", "新闻详情");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                UIUtils.getContext().startActivity(intent);
                            }
                        });
                        return;
                    }
                    mNewsAdapter.setNewses(result);
                    mNewsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
