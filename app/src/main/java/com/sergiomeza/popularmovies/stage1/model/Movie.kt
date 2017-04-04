package com.sergiomeza.popularmovies.stage1.model

import com.sergiomeza.popularmovies.stage1.util.Const.Companion.BASE_IMAGE_URL
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sergiomeza on 4/4/17.
 * Data class of kotlin, see at: https://kotlinlang.org/docs/reference/data-classes.html
 * Used to parse the api response to match the fields of the JSON
 */
data class Movie(val id: Int, val poster_path: String, val overview: String,
                 val release_date: String, val original_title: String,
                 val title: String, val backdrop_path: String,
                 val vote_average: Double): Serializable {

    /**
     * Concatenation of kotlin making my life so much easier :))
     */
    fun smallImageUrl(): String {
        return "${BASE_IMAGE_URL}w185$poster_path"
    }

    fun mediumImageUrl(): String {
        return "${BASE_IMAGE_URL}w342$poster_path"
    }

    fun mediumbackgroundUrl(): String {
        return "${BASE_IMAGE_URL}w342$backdrop_path"
    }

    fun parseDate(): String {
        val mDate = SimpleDateFormat("yyyy-MM-dd").parse(this.release_date)
        val mCalendar = Calendar.getInstance()
        mCalendar.time = mDate
        return mCalendar.get(Calendar.YEAR).toString()
    }
}