package com.dirzaaulia.multimodulesample.data.source

import com.dirzaaulia.multimodulesample.domain.util.ResponseResult
import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie

interface ServiceDataSource {
  suspend fun discoverMovie(page: Int): ResponseResult<DiscoverMovie>
}