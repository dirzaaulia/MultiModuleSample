package com.dirzaaulia.multimodulesample.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dirzaaulia.multimodulesample.data.paging.MoviePagingSource
import com.dirzaaulia.multimodulesample.data.services.Service
import com.dirzaaulia.multimodulesample.domain.model.Movie
import com.dirzaaulia.multimodulesample.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow

class ServiceRepositoryImpl(
  private val remoteDataSource: ServiceDataSource
): ServiceRepository {
  override fun discoverMovie(): Flow<PagingData<Movie>> =
    Pager(PagingConfig(pageSize = 50)) {
      MoviePagingSource(remoteDataSource = remoteDataSource)
    }.flow
}