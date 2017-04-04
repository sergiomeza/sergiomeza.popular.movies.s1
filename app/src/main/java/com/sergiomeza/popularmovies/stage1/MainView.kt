package com.sergiomeza.popularmovies.stage1

import com.sergiomeza.popularmovies.stage1.model.ApiResponse
import com.sergiomeza.popularmovies.stage1.model.Movie

/**
 * Created by sergiomeza on 4/4/17.
 */
interface MainView {
    fun showLoading(mRefresh: Boolean = false)

    fun hideLoading(mError: Boolean)

    fun onRequestSuccess(mResult: ApiResponse)

    fun onRequestError(mError: String)
}