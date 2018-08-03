package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class NewsPresenter {
    private static final String BASE_URL = "https://360game.vn/";

    private NewsApiEndPoint mDataService;

    private NewsView mView;

    private Context mContext;

    private NormalPaging mPaging;

    public NewsPresenter(Context context,NormalPaging paging){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        mDataService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NewsApiEndPoint.class);

        this.mContext = context;

        this.mPaging = paging;
    }

    public void attachView (NewsView view){
        mView = view;
    }

    public void detachView (){
        mView = null;
    }

    public void fetchNewsList(int page, int pageSize){
        mView.showProgressIndicator();

        if(!NetworkUtils.isNetworkConnected(mContext)){
            mView.showNoNetworkState();
            return;
        }

        mView.hideNoNetworkState();
        Call<NewsPackage> call = mDataService.getAPI(page, pageSize);
        call.enqueue(new Callback<NewsPackage>() {
            @Override
            public void onResponse(Call<NewsPackage> call, Response<NewsPackage> response) {
                NewsPackage newsPackage = response.body();

                if (newsPackage != null){
                    String errorCode = newsPackage.getErrorCode();
                    if ("0".equals(errorCode)){
                        if (mPaging != null) {
                            mPaging.next();
                        }
                        List<NewsData> newsDataList = newsPackage.getData();
                        List<News> resultList = new ArrayList<>();

                        for(NewsData newsData: newsDataList){
                            resultList.add(new News(newsData));
                        }
                        mView.showNewsList(resultList);
                        mView.hideProgressIndicator();
                        Log.e("fetch successfully", "successfully");

                    }else{
                        Toast.makeText(mContext,newsPackage.getErrorMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsPackage> call, Throwable t) {
                if (t instanceof UnknownHostException || t instanceof SocketTimeoutException) {
                    Toast.makeText(mContext, "Cannot find server or connection interrupted", Toast.LENGTH_LONG).show();
                }
                Log.d(TAG, String.format("onFailure: fetch failed, throwable: %s", t.toString()));
            }
        });
    }
}
