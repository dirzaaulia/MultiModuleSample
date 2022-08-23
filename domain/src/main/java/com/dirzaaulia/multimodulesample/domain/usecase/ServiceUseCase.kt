package com.dirzaaulia.multimodulesample.domain.usecase

import androidx.paging.PagingData
import com.dirzaaulia.multimodulesample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ServiceUseCase {
  fun discoverMovie(): Flow<PagingData<Movie>>
}