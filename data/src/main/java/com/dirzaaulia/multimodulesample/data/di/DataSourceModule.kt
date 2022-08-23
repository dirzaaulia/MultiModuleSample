package com.dirzaaulia.multimodulesample.data.di

import com.dirzaaulia.multimodulesample.data.services.Service
import com.dirzaaulia.multimodulesample.data.source.ServiceDataSource
import com.dirzaaulia.multimodulesample.data.source.ServiceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

  @Provides
  @Singleton
  fun provideServiceRemoteDataSource(service: Service): ServiceDataSource {
    return ServiceRemoteDataSourceImpl(service)
  }
}