package dev.passerby.tourtestproject.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://rsttur.ru/api/base-app/"
    private val mHttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(BODY)

    private val mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    private val mGsonConverterFactory = GsonConverterFactory.create()

    private val mRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(mOkHttpClient)
        .addConverterFactory(mGsonConverterFactory)
        .build()

    val apiService: ApiService = mRetrofit.create(ApiService::class.java)
}