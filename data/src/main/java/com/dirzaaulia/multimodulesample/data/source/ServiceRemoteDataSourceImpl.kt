package com.dirzaaulia.multimodulesample.data.source

import com.dirzaaulia.multimodulesample.domain.util.ResponseResult
import com.dirzaaulia.multimodulesample.domain.util.executeWithResponse
import com.dirzaaulia.multimodulesample.data.network.KtorHttpClient
import com.dirzaaulia.multimodulesample.data.network.KtorService
import com.dirzaaulia.multimodulesample.data.response.DiscoverMovieResponse
import com.dirzaaulia.multimodulesample.data.services.Service
import com.dirzaaulia.multimodulesample.domain.model.DiscoverMovie
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceRemoteDataSourceImpl(
  private val service: Service,
  private val ktor: KtorHttpClient
): ServiceDataSource {

  override suspend fun discoverMovie(page: Int): ResponseResult<DiscoverMovie> {
    return withContext(Dispatchers.IO) {
      executeWithResponse {
        val response = ktor.ktorHttpClient.get(KtorService.discoverMovie) {
          parameter("api_key", "2fce7a56fdfd95647be5e0d638d81da9")
          parameter("sort_by", "popularity.desc")
          parameter("include_adult", false)
          parameter("include_video", false)
          parameter("page", page)
        }
        response.body<DiscoverMovieResponse>().let {
          DiscoverMovieResponse.transformToDiscoverMovie(it)
        }
      }
    }
  }
}