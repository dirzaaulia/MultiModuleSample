package com.dirzaaulia.multimodulesample.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    val genreId: List<Int>? = null,
    val id: Int? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null,
    val budget: Long? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanies>? = null,
    val revenue: Long? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
) : Parcelable