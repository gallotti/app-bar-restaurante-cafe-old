package com.gallotti.appcafebarrestaurante.di

import com.gallotti.appcafebarrestaurante.feature.detail.DetailStoreViewModel
import com.gallotti.appcafebarrestaurante.feature.store.StoreListViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {

    viewModel { StoreListViewModel(get()) }
    viewModel { DetailStoreViewModel(get()) }

}