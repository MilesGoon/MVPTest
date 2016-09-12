package com.miles.mvptest.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * 全局盒子,里面放置一些全局的变量或者方法,Application其实是一个单例
 */
public class BaseApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static long mMainThreadId;
    private static Thread mMainThread;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    @Override
    public void onCreate() {// 程序入口方法 创建一些常见的变量
        // 1.上下文
        mContext = getApplicationContext();

        // 2.创建一个handler
        mHandler = new Handler();

        // 3.得到一个主线程id
        mMainThreadId = android.os.Process.myTid();

        // 4.得到主线程
        mMainThread = Thread.currentThread();

        initImageLoader(this);

        super.onCreate();
    }

    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/mvp/cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)).discCache(new UnlimitedDiscCache(cacheDir))
                .discCacheSize(40 * 1024 * 1024).discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();

        ImageLoader.getInstance().init(config);
    }

}
