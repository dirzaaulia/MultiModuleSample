package com.dirzaaulia.multimodulesample.data.source

import com.bts.id.domain.utils.ResponseResult
import com.bts.id.domain.utils.executeWithResponse
import com.dirzaaulia.multimodulesample.data.response.DiscoverMovieResponse
import com.dirzaaulia.multimodulesample.data.services.Service
import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ServiceRemoteDataSourceImpl(
  private val service: Service
): ServiceDataSource {
  override suspend fun discoverMovie(page: Int): ResponseResult<DiscoverMovie> {
    return withContext(Dispatchers.IO) {
      executeWithResponse {
        val response = service.discoverMovie(page = page)
        response.body()?.let {
          DiscoverMovieResponse.transformToDiscoverMovie(it)
        } ?: run {
          throw HttpException(response)
        }
      }
    }
  }
}