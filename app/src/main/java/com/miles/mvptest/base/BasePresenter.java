package com.miles.mvptest.base;

import com.miles.mvptest.network.ServerApi;
import com.miles.mvptest.network.ServerApiImpl;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 绑定/解绑Activity 访问网络
 *
 * <T> -> MainViewInterface
 */
public class BasePresenter<T> {
    protected Reference<T> mViewRef;
    protected ServerApi mServerApi;

    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
        mServerApi = ServerApiImpl.getInstance();
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
