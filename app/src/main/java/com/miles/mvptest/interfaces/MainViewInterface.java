package com.miles.mvptest.interfaces;

import android.support.v4.app.Fragment;

/**
 * MainActivity <-> MainPresenter
 */
public interface MainViewInterface {
    void init();

    void switchFragment(Fragment newFragment, String title);
}
