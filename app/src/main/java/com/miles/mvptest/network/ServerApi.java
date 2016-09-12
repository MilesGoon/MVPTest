package com.miles.mvptest.network;


import com.miles.mvptest.entites.Joke;
import com.miles.mvptest.entites.News;
import com.miles.mvptest.entites.WeChatArticle;
import com.miles.mvptest.interfaces.listener.DataListener;

import java.util.List;

public interface ServerApi {

    void fetchJokes(int page, DataListener<List<Joke>> listener);

    void fetchNews(String type, DataListener<List<News>> listener);

    void fetchWeChatArticles(int page, DataListener<List<WeChatArticle>> listener);
}
