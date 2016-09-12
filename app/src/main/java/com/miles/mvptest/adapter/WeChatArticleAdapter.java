package com.miles.mvptest.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miles.mvptest.R;
import com.miles.mvptest.entites.WeChatArticle;
import com.miles.mvptest.utils.LoadImageAsynTask;

import java.util.List;

public class WeChatArticleAdapter extends BaseAdapter<WeChatArticle, RecyclerView.ViewHolder> {

    public void setWeChatArticles(List<WeChatArticle> weChatArticles) {
        mDataSet.clear();
        mDataSet.addAll(weChatArticles);
    }

    public WeChatArticleAdapter(List<WeChatArticle> articles) {
        super(articles);
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, WeChatArticle item, int position) {
        if (viewHolder instanceof NewsViewHolder) {
            bindNewsToNewsItem((NewsViewHolder) viewHolder, item);
        }
    }

    private void bindNewsToNewsItem(NewsViewHolder viewHolder, WeChatArticle item) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvFrom.setText(item.source);
        viewHolder.tvDate.setText("来自：");
        if (TextUtils.isEmpty(item.firstImg)) {
            viewHolder.llImgContainer.setVisibility(View.GONE);
        } else {
            viewHolder.llImgContainer.setVisibility(View.VISIBLE);
            new LoadImageAsynTask().loadDrawable(item.firstImg, viewHolder.ivNewsPhoto);
        }
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
        LinearLayout llImgContainer;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvFrom = (TextView) itemView.findViewById(R.id.tv_from);
            ivNewsPhoto = (ImageView) itemView.findViewById(R.id.iv_news_photo);
            llImgContainer = (LinearLayout) itemView.findViewById(R.id.ll_image_container);
        }
    }
}
