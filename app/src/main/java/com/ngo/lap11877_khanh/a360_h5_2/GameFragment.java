package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;


public class GameFragment extends Fragment implements GameView, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 30;

    private GameAdapter mGameAdapter;

    private GamePresenter mGamePresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ProgressBar mProgressBar;

    private ConstraintLayout mConnectionFailed;

    private RecyclerView mRecyclerView;

    private Button mRetryButton;

    private NormalPaging mPaging = new NormalPaging(PAGE_SIZE);


    public static GameFragment newInstance (){
        return new GameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_news_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getActivity();

        initViewsById(view);
        
        mGameAdapter = new GameAdapter(context);
        mRecyclerView.setAdapter(mGameAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mGameAdapter.setOnClickDetailListener(new GameAdapter.OnClickDetailListener() {
            @Override
            public void onClickDetail(Game game) {
                GameFragment.this.startActivity(WebViewActivity.intentFor(GameFragment.this.getActivity(), game.getAppDetails()));
            }
        });

        mGamePresenter = new GamePresenter(context.getApplicationContext(), mPaging);
        mGamePresenter.attachView(this);

        mGamePresenter.fetchGameList(mPaging.getNext(), mPaging.getPageSize());

        mRecyclerView.addOnScrollListener(createOnScrollListener(linearLayoutManager));

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaging.reset();
                mGamePresenter.fetchGameList(mPaging.getNext(), mPaging.getPageSize());
            }
        });
    }

    private void initViewsById(View view){
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mConnectionFailed = view.findViewById(R.id.connection_failed_layout);
        mRetryButton = view.findViewById(R.id.try_again_button);
    }

    private RecyclerView.OnScrollListener createOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        final EndlessScrollDownListener endlessScrollDownListener = new EndlessScrollDownListener(mPaging, layoutManager);
        endlessScrollDownListener.setPageLoader(new EndlessScrollDownListener.PageLoader() {
            @Override
            public void loadNextPage() {
                mGamePresenter.fetchGameList(mPaging.getNext(), mPaging.getPageSize());
            }
        });
        return endlessScrollDownListener;
    }


    @Override
    public void onDestroy() {
        mGamePresenter.detachView();
        super.onDestroy();
    }

    public void showGameList(List<Game> mGames){
        if (mPaging.getCurrent() == 1){
            mGameAdapter.setGameList(mGames);
        }else{
            mGameAdapter.appendGameList(mGames);
        }
    }

//    @Override
//    public void showUpdatedGameList(List<Game> mGames) {
//        mGameAdapter.appendGameList(mGames);
//    }

    @Override
    public void onRefresh() {
        mPaging.reset();
        mGamePresenter.fetchGameList(mPaging.getNext(), mPaging.getPageSize());
    }

    @Override
    public void showProgressIndicator(){
        if(!mSwipeRefreshLayout.isRefreshing() && !mPaging.isLoading()){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressIndicator(){
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
}