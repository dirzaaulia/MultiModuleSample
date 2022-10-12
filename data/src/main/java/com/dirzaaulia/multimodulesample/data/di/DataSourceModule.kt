package com.dirzaaulia.multimodulesample.data.di

import android.content.Context
import com.dirzaaulia.multimodulesample.data.network.KtorHttpClient
import com.dirzaaulia.multimodulesample.data.services.Service
import com.dirzaaulia.multimodulesample.data.source.ServiceDataSource
import com.dirzaaulia.multimodulesample.data.source.ServiceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

  @Provides
  @Singleton
  fun provideKtor(@ApplicationContext context: Context): KtorHttpClient {
    return KtorHttpClient(context)
  }

  @Provides
  @Singleton
  fun provideServiceRemoteDataSource(
    service: Service,
    ktor: KtorHttpClient
  ): ServiceDataSource {
    return ServiceRemoteDataSourceImpl(service, ktor)
  }


}