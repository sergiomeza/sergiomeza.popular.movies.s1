package com.sergiomeza.popularmovies.stage1.model

import java.io.Serializable

/**
 * Created by sergiomeza on 4/4/17.
 * Data class of kotlin, see at: https://kotlinlang.org/docs/reference/data-classes.html
 * Used to parse the api response to match the fields of the JSON
 */
data class Movie(val id: Int, val poster_path: String, val overview: String,
                 val release_date: String, val original_title: String,
                 val title: String): Serializable {

    /**
     * Concatenation of kotlin making my life so much easier :))
     */
    fun smallImageUrl(): String {
        return "http://image.tmdb.org/t/p/w185$poster_path"
    }
}