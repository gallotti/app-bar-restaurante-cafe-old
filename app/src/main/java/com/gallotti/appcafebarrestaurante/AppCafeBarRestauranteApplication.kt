package com.gallotti.appcafebarrestaurante

import android.app.Application
import org.koin.android.ext.android.startKoin
import com.gallotti.appcafebarrestaurante.di.*

class AppCafeBarRestauranteApplication: Application()  {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin(
            arrayListOf(
                networkModule,
                repositoryModule,
                viewModelModule
            ))
    }
}