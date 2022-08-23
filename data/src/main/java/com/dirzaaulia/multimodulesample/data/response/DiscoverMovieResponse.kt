package com.dirzaaulia.multimodulesample.data.response

import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie
import com.dirzaaulia.multimodulesample.domain.model.Movie
import com.squareup.moshi.Json

data class DiscoverMovieResponse(
    val page: Int?,
    val results: List<Movie> = emptyList(),
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?,
) {
    companion object {
        fun transformToDiscoverMovie(response: DiscoverMovieResponse): DiscoverMovie {
            return DiscoverMovie(
                page = response.page,
                data = response.results,
                totalPages = response.totalPages,
                totalResults = response.totalResults
            )
        }
    }
}