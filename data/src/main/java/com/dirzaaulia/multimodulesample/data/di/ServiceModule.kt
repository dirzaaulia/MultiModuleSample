package com.dirzaaulia.multimodulesample.data.di

import android.content.Context
import com.dirzaaulia.multimodulesample.data.services.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

  @Singleton
  @Provides
  fun provideService(@ApplicationContext context: Context): Service {
    return Service.create(context)
  }
}