package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

public class NewsFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener{
    private static final int PAGE_SIZE = 30;

    private NewsAdapter mNewsAdapter;

    private NewsPresenter mNewsPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ProgressBar mProgressBar;

    private ConstraintLayout mConnectionFailed;

    private RecyclerView mRecyclerView;

    private NormalPaging mPaging = new NormalPaging(PAGE_SIZE);


    public static NewsFragment newInstance (){
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getActivity();

        initViewsById(view);

        mNewsAdapter = new NewsAdapter(context);
        mRecyclerView.setAdapter(mNewsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mNewsPresenter = new NewsPresenter(context, mPaging);
        mNewsPresenter.attachView(this);
        mNewsPresenter.fetchNewsList(mPaging.getNext(), mPaging.getPageSize());

        mRecyclerView.addOnScrollListener(createOnScrollListener(linearLayoutManager));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));
    }

    private void initViewsById(View view){
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mConnectionFailed = view.findViewById(R.id.connection_failed_layout);
    }

    private RecyclerView.OnScrollListener createOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        final EndlessScrollDownListener endlessScrollDownListener = new EndlessScrollDownListener(mPaging, layoutManager);
        endlessScrollDownListener.setPageLoader(new EndlessScrollDownListener.PageLoader() {
            @Override
            public void loadNextPage() {
                fetchGameData(mPaging.getNext(), mPaging.getPageSize());
            }
        });
        return endlessScrollDownListener;
    }

    private void fetchGameData (int page, int pageSize){
        mNewsPresenter.fetchNewsList(page, pageSize);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mNewsPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showNewsList(List<News> mNews) {
        if (mPaging.getCurrent() == 1){
            mNewsAdapter.setNewsList(mNews);
        }else{
            mNewsAdapter.appendNewsList(mNews);
        }
    }

    @Override
    public void showProgressIndicator() {
        if (mSwipeRefreshLayout.isRefreshing()){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressIndicator() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoNetworkState() {
        mConnectionFailed.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoNetworkState() {
        mConnectionFailed.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        mPaging.reset();
        mNewsPresenter.fetchNewsList(mPaging.getNext(), mPaging.getPageSize());
    }
}
