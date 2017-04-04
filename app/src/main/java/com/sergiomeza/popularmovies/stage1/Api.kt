package com.sergiomeza.popularmovies.stage1

import com.sergiomeza.popularmovies.stage1.model.ApiResponse
import com.sergiomeza.popularmovies.stage1.util.Const
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sergiomeza on 4/4/17.
 */
interface Api {
    @GET("popular/")
    fun getPopular(@Query("api_key")mApiKey: String = Const.API_KEY,
                   @Query("page")page: Int = 1): Call<ApiResponse>

    @GET("top_rated/")
    fun getTopRated(@Query("api_key")mApiKey: String = Const.API_KEY,
                    @Query("page")page: Int = 1): Call<ApiResponse>
}