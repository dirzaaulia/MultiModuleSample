package com.dirzaaulia.multimodulesample.domain.usecase

import androidx.paging.PagingData
import com.dirzaaulia.multimodulesample.domain.model.Movie
import com.dirzaaulia.multimodulesample.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow

class ServiceUseCaseImpl(
  private val repository: ServiceRepository
): ServiceUseCase {
  override fun discoverMovie() = repository.discoverMovie()
}