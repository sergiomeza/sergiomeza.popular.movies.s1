package com.sergiomeza.popularmovies.stage1.ui.view

import com.sergiomeza.popularmovies.stage1.model.Movie

/**
 * Created by sergiomeza on 4/4/17.
 */
interface DetailView {
    fun onMovieLoaded(mMovie: Movie)

    fun onMovieError(mError: String)
}