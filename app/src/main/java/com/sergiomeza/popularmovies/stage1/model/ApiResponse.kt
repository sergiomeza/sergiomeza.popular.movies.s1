package com.sergiomeza.popularmovies.stage1.model

/**
 * Created by sergiomeza on 4/4/17.
 */
data class ApiResponse(val page: Int, val results: List<Movie>, val total_results: Int,
                       val total_pages: Int) {
}