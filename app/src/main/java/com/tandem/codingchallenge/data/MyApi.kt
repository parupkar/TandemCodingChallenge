package com.tandem.codingchallenge.data

import com.tandem.codingchallenge.data.models.CommunitiesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("community_{page}.json")
    suspend fun getCommunitiesData(
        @Path("page") page: Int
    ): CommunitiesResponse

    companion object {

        private const val BASE_URL = "https://tandem2019.web.app/api/"

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}

