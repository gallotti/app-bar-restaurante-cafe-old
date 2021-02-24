package com.gallotti.appcafebarrestaurante.di

import com.gallotti.appcafebarrestaurante.network.api.StoresApi
import okhttp3.OkHttpClient
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = applicationContext {
    bean {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("")
            .client(OkHttpClient.Builder().build())
            .build()
    }

    bean {
        val retrofit: Retrofit = get()
        retrofit.create(StoresApi::class.java)
    }
}
