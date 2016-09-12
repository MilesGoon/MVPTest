package com.miles.mvptest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 绑定/解绑Presenter
 *
 * <V> -> <MainViewInterface>
 * <T> -> <MainPresenter>
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    @Override
    public void onClick(View v) {

    }
}
