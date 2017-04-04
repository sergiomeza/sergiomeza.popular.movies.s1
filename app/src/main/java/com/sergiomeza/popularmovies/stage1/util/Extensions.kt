package com.sergiomeza.popularmovies.stage1.util

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sergiomeza on 4/4/17.
 */


 /**
  * Recyclerview extension for easy initialization
  */
fun RecyclerView.init(mOrientation: Int = LinearLayoutManager.VERTICAL){
    this.setHasFixedSize(true);
    val mLinearManager = LinearLayoutManager(this.context)
    mLinearManager.orientation = mOrientation
    this.layoutManager = mLinearManager
    this.itemAnimator = DefaultItemAnimator()
}

/**
 * Retrofit Singleton with base URL passed as a optional with a GSON builtInt Transformation
 */
fun retrofit(mUrl: String = Const.API_BASE_URL) = Retrofit.Builder()
        .baseUrl(mUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

/**
 * Extension method on the context to know if the user has an active internet conection
 */
fun Context.isConnectingToInternet(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as
            ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting
}

class Const {
    companion object {
        val API_BASE_URL = "https://api.themoviedb.org/3/movie/"
        val BASE_IMAGE_URL = "http://image.tmdb.org/t/p/"
        val API_KEY = "<YOUR-IP-ID>"
        val DETAIL_DATA = "MOVIE_DATA"
    }
}

enum class ApiMethods(val state: String) {
    POPULAR("POPULAR"),
    TOP_RATED("TOP_RATED")
}

