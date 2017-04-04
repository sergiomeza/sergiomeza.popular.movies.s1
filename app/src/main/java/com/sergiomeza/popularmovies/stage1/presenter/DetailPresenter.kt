package com.sergiomeza.popularmovies.stage1.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sergiomeza.popularmovies.stage1.R
import com.sergiomeza.popularmovies.stage1.model.Movie
import com.sergiomeza.popularmovies.stage1.ui.view.DetailView
import com.sergiomeza.popularmovies.stage1.util.Const

/**
 * Created by sergiomeza on 4/4/17.
 */
class DetailPresenter(val mDetailView : DetailView, val mContext: Context) {
    fun loadMovie(mIntent: Intent){
        if(mIntent.extras.containsKey(Const.DETAIL_DATA)) {
            val mMovie = mIntent.getSerializableExtra(Const.DETAIL_DATA) as Movie
            mDetailView.onMovieLoaded(mMovie)
        }
        else {
            mDetailView.onMovieError(mContext.getString(R.string.error_no_data_detail))
        }
    }
}