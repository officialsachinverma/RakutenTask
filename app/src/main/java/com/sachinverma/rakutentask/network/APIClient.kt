package com.sachinverma.rakutentask.network

import com.sachinverma.rakutentask.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sachin Verma on 2020-02-09.
 */
object APIClient {

    private var retrofit: Retrofit? = null

    init {
        val interceptor = RetrofitInterceptor()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.Network.apiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getClient() = retrofit

}