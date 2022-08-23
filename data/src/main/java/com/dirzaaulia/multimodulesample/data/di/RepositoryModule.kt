package com.dirzaaulia.multimodulesample.data.di

import com.dirzaaulia.multimodulesample.data.source.ServiceDataSource
import com.dirzaaulia.multimodulesample.data.source.ServiceRepositoryImpl
import com.dirzaaulia.multimodulesample.domain.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
  @Provides
  @Singleton
  fun provideServiceRepository(
    remoteDataSource: ServiceDataSource
  ): ServiceRepository {
    return ServiceRepositoryImpl(remoteDataSource)
  }
}