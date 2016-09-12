package com.miles.mvptest.interfaces;


import com.miles.mvptest.adapter.JokeAdapter;

public interface JokeViewInterface {
    void showLoading();

    void hideLoading();

    void setAdapter(JokeAdapter jokeAdapter);
}
