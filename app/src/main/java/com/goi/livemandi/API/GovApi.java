package com.goi.livemandi.API;

import com.goi.livemandi.Models.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GovApi {

    @GET("9ef84268-d588-465a-a308-a864a43d0070?")
    Call<Root> getMandiData(@Query("api-key") String api_key,@Query("format") String format,
                            @Query("offset") int offset,@Query("limit") int limit,
                            @Query("filters[state]") String state,@Query("filters[district]") String filter,
                            @Query("filters[min_price]") String min_price, @Query("filters[max_price]") String max_price);
}
