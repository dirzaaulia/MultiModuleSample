package com.dirzaaulia.multimodulesample.data.response

import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie
import com.dirzaaulia.multimodulesample.domain.model.Movie
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMovieResponse(
    val page: Int = 0,
    val results: List<Movie> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0,
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