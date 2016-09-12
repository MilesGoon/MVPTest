package com.miles.mvptest.presenter;


import com.miles.mvptest.adapter.JokeAdapter;
import com.miles.mvptest.base.BasePresenter;
import com.miles.mvptest.entites.Joke;
import com.miles.mvptest.interfaces.JokeViewInterface;
import com.miles.mvptest.interfaces.listener.DataListener;

import java.util.List;

public class JokePresenter extends BasePresenter<JokeViewInterface> {
    private JokeAdapter mJokeAdapter;
    private boolean isFirst = true;
    private boolean isFirst2 = true;

    public void fetchJokes() {
        getView().showLoading();
        mServerApi.fetchJokes(getRandomNum(100), new DataListener<List<Joke>>() {
            @Override
            public void onComplete(List<Joke> result) {
                getView().hideLoading();

                if (result != null) {
                    if (isFirst) {
                        mJokeAdapter = new JokeAdapter(result);
                        getView().setAdapter(mJokeAdapter);
                        isFirst = false;
                        return;
                    }

                    mJokeAdapter.setJokeList(result);
                    mJokeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 获取随机数
     * number 取值范围（1 ~ number）
     */
    private int getRandomNum(int number) {
        if (isFirst2) {
            isFirst2 = false;
            return 1;
        }
        return (int) (Math.random() * number);
    }
}
