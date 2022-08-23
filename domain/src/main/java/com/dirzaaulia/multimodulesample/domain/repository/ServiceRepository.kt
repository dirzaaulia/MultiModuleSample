package com.dirzaaulia.multimodulesample.domain.repository

import androidx.paging.PagingData
import com.dirzaaulia.multimodulesample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
  fun discoverMovie(): Flow<PagingData<Movie>>
}