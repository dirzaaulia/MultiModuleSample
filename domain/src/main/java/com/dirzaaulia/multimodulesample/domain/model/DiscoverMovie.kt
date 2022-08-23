package com.dirzaaulia.multimodulesample.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMovie(
  val page: Int?,
  val data: List<Movie> = emptyList(),
  val totalPages: Int?,
  val totalResults: Int?,
): Parcelable