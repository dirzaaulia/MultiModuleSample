package com.dirzaaulia.multimodulesample.data.source

import com.bts.id.domain.utils.ResponseResult
import com.dirzaaulia.multimodulesample.data.response.DiscoverMovieResponse
import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie

interface ServiceDataSource {
  suspend fun discoverMovie(page: Int): ResponseResult<DiscoverMovie>
}