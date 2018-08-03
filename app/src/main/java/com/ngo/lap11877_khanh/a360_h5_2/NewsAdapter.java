package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private final List<News> mNewsList = new ArrayList<>();

    private Context mContext;

    public NewsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList (List<News> newsList) {
        mNewsList.clear();

        if (newsList!= null && !newsList.isEmpty()){
            mNewsList.addAll(newsList);
        }

        notifyDataSetChanged();
    }

    public void appendNewsList (List<News> newsList){
        if (newsList!= null && !newsList.isEmpty()){
            mNewsList.addAll(newsList);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mNewsTitle, mDateCreated;

        public ViewHolder(View itemView) {
            super(itemView);
            mNewsTitle = itemView.findViewById(R.id.news_title);
            mDateCreated = itemView.findViewById(R.id.news_date);
        }

        public void bindView(News mNews){
            mNewsTitle.setText(mNews.getTitle());
            mDateCreated.setText(mNews.getDateCreated());
        }
    }
}