package com.dirzaaulia.multimodulesample.domain.di

import com.dirzaaulia.multimodulesample.domain.repository.ServiceRepository
import com.dirzaaulia.multimodulesample.domain.usecase.ServiceUseCase
import com.dirzaaulia.multimodulesample.domain.usecase.ServiceUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideServiceUseCase(
    repository: ServiceRepository
  ): ServiceUseCase {
    return ServiceUseCaseImpl(repository)
  }
}