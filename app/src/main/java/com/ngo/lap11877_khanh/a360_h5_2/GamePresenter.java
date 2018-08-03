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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class GamePresenter {
    private static final String BASE_URL = "https://360game.vn/";

    private final GameApiEndPoint mDataService;

    private GameView mView;

    private final Context mContext;

    private NormalPaging mPaging;

    public GamePresenter(Context context, NormalPaging paging) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        mDataService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GameApiEndPoint.class);

        this.mContext = context;
        this.mPaging = paging;
    }

    public void attachView (GameView view){
        mView = view;
    }

    public void detachView (){
        mView = null;
    }

    public void checkNetworkState(){

    }

    public void fetchGameList(int page, int pageSize){

        mView.showProgressIndicator();

        if(!NetworkUtils.isNetworkConnected(mContext)){
            mView.showNoNetworkState();
            mView.hideProgressIndicator();
            return;
        }
        mView.hideNoNetworkState();

        Call<GamePackage> call = mDataService.getAPI(page, pageSize);
        mPaging.setLoading(true);
        call.enqueue(new Callback<GamePackage>() {
            @Override
            public void onResponse(Call<GamePackage> call, Response<GamePackage> response) {
                GamePackage gameResponse = response.body();

                if (gameResponse != null){
                    String errorCode = gameResponse.getErrorCode();
                    if ("0".equals(errorCode)) {
                        if (mPaging != null) {
                            mPaging.next();
                        }

                        List<Game> resultList = new ArrayList<>();
                        List<GameData> gameList = gameResponse.getData();

                        for (GameData gameData: gameList){
                            Game mGame = new Game(gameData);
                            resultList.add(mGame);
                        }
                        mView.showGameList(resultList);
                        mView.hideProgressIndicator();
                        Log.d(TAG, "onResponse: fetch successfully");
                    } else {
                        Toast.makeText(mContext, gameResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GamePackage> call, Throwable t) {
                if (t instanceof UnknownHostException || t instanceof SocketTimeoutException) {
                    Toast.makeText(mContext, "Cannot find server or connection interrupted", Toast.LENGTH_LONG).show();
                }
                Log.d(TAG, String.format("onFailure: fetch failed, throwable: %s", t.toString()));
            }
        });
    }
}
