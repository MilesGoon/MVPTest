package com.miles.mvptest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miles.mvptest.R;
import com.miles.mvptest.entites.News;
import com.miles.mvptest.utils.LoadImageAsynTask;

import java.util.List;

public class NewsAdapter extends BaseAdapter<News, RecyclerView.ViewHolder> {
    private LoadImageAsynTask mLoadImageAsynTask;

    public void setNewses(List<News> newses) {
        mDataSet.clear();
        mDataSet.addAll(newses);
    }

    public NewsAdapter(List<News> newsList) {
        super(newsList);
        mLoadImageAsynTask = new LoadImageAsynTask();
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, News item, int position) {
        if (viewHolder instanceof NewsViewHolder) {
            bindNewsToNewsItem((NewsViewHolder) viewHolder, item);
        }
    }

    private void bindNewsToNewsItem(NewsViewHolder viewHolder, News item) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDate.setText(item.date);
        viewHolder.tvFrom.setText(item.author_name);
        mLoadImageAsynTask.loadDrawable(item.thumbnail_pic_s, viewHolder.ivNewsPhoto);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createNewsViewHolder(parent);
    }

    protected RecyclerView.ViewHolder createNewsViewHolder(ViewGroup parent) {
        return new NewsViewHolder(inflateItemView(parent, R.layout.item_news));
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDate, tvFrom;
        ImageView ivNewsPhoto;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvFrom = (TextView) itemView.findViewById(R.id.tv_from);
            ivNewsPhoto = (ImageView) itemView.findViewById(R.id.iv_news_photo);
        }
    }
}
