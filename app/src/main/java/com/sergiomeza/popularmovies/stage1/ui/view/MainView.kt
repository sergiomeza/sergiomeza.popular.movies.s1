package com.sergiomeza.popularmovies.stage1.ui.view

import com.sergiomeza.popularmovies.stage1.model.ApiResponse

/**
 * Created by sergiomeza on 4/4/17.
 */
interface MainView {
    fun showLoading(mRefresh: Boolean = false)

    fun hideLoading(mError: Boolean)

    fun onRequestSuccess(mResult: ApiResponse)

    fun onRequestError(mError: String)
}