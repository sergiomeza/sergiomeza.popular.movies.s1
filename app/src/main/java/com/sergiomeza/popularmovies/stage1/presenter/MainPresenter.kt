package com.sergiomeza.popularmovies.stage1.presenter

import android.content.Context
import com.sergiomeza.popularmovies.stage1.Api
import com.sergiomeza.popularmovies.stage1.MainView
import com.sergiomeza.popularmovies.stage1.R
import com.sergiomeza.popularmovies.stage1.model.ApiResponse
import com.sergiomeza.popularmovies.stage1.util.isConnectingToInternet
import com.sergiomeza.popularmovies.stage1.util.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sergiomeza on 4/4/17.
 */
class MainPresenter(val mMainView :MainView, val mContext: Context) {
    /**
     * Initialize the retrofit Singleton
     */
    val api = retrofit().create(Api::class.java)
    var mCall: Call<ApiResponse>? = null

    /**
     * Making the Http request with retrofit using the GSON converter
     * @param mRefresh is passed to know if the loading state is the initial with the progressbar
     *                 or if it's from swipe refresh
     */
    fun getMovies(mPage: Int = 1, mRefresh: Boolean = false){

        /**
         * if we have an active internet connection
         */
        if(mContext.isConnectingToInternet()){
            /**
             * Show loading state
             */
            mMainView.showLoading(mRefresh)

            /**
             * Assing the mCall variable to the request
             */
            mCall = api.getPopular()
            mCall?.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                    mMainView.hideLoading(true)
                    mMainView.onRequestError(mContext.getString(R.string.error_request, "${t?.cause}"))
                }

                override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                    try {
                        if (response?.body() != null) {
                            mMainView.hideLoading(false)
                            /**
                             * Smart casting of kotlin with null safety
                             */
                            mMainView.onRequestSuccess(response.body())
                        }
                    }
                    catch (mException: Exception){
                        mMainView.hideLoading(true)
                        mMainView.onRequestError("Errosillo")
                    }
                }
            })
        }
        else {
            /**
             * Show the no internet connection error on the view
             */
            mMainView.onRequestError(mContext.getString(R.string.error_no_internet))
        }
    }

    /**
     * When the activity is destroyer, we cancel all the request if the call is not null
     * to avoid memory leaks and errors on View when the call finishes
     */
    fun onDestroy(){
        if(mCall != null){
            mCall?.cancel()
        }
    }
}