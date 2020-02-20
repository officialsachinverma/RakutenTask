package com.sachinverma.rakutentask.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Sachin Verma on 2020-02-09.
 */

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}