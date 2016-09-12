package com.miles.mvptest.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miles.mvptest.R;
import com.miles.mvptest.entites.Joke;

import java.util.List;

public class JokeAdapter extends BaseAdapter<Joke, RecyclerView.ViewHolder> {



    public void setJokeList(List<Joke> jokeList) {
        mDataSet.clear();
        mDataSet.addAll(jokeList);
    }

    public JokeAdapter(List<Joke> dataSet) {
        super(dataSet);
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, Joke item, int position) {

        if (viewHolder instanceof JokeViewHolder) {
            JokeViewHolder jokeViewHolder = (JokeViewHolder) viewHolder;
            jokeViewHolder.mContent.setText((position + 1) + ". " + Html.fromHtml(item.content));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeViewHolder(inflateItemView(parent, R.layout.item_joke));
    }

    class JokeViewHolder extends RecyclerView.ViewHolder {

        TextView mContent;

        public JokeViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.tv_joke);
        }
    }

}
