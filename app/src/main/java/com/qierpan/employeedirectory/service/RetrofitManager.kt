package com.qierpan.employeedirectory.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private var okHttpClient: OkHttpClient

    init {
        val timeout = 60
        okHttpClient =
            OkHttpClient.Builder().connectTimeout(timeout.toLong(), TimeUnit.SECONDS).writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeout.toLong(), TimeUnit.SECONDS).build()
    }

    private fun retrofitInstanceBuilder(url: String): Retrofit {
        return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(
            okHttpClient).build()
    }

    val employeeDirectoryService: Retrofit
        get() {
            val EMPLOYEE_LIST_URL = "https://s3.amazonaws.com/sq-mobile-interview/"
            return retrofitInstanceBuilder(EMPLOYEE_LIST_URL)
        }
}