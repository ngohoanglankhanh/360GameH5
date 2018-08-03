package com.ngo.lap11877_khanh.a360_h5_2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiEndPoint {
    @GET ("h5/mapi/get-list-news")
    Call<NewsPackage> getAPI(@Query("page") int page,
                             @Query("total") int total);
}
