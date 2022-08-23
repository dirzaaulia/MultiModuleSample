package com.dirzaaulia.multimodulesample.data.services

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dirzaaulia.multimodulesample.data.response.DiscoverMovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {
  @GET("discover/movie")
  suspend fun discoverMovie(
    @Query("api_key") apiKey: String = "2fce7a56fdfd95647be5e0d638d81da9",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("include_adult") isIncludeAdult: Boolean = false,
    @Query("include_video") isIncludeVideo: Boolean = false,
    @Query("page") page: Int
  ): Response<DiscoverMovieResponse>

  companion object {
    fun create(context: Context): Service {
      val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

      val client = OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

      val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

      return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(Service::class.java)
    }
  }
}