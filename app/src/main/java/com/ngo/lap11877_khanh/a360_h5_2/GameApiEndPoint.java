package com.ngo.lap11877_khanh.a360_h5_2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiEndPoint {
    @GET ("h5/mapi/get-list-app")
    Call<GamePackage> getAPI(@Query("page") int page,
                             @Query("total") int total);
}
