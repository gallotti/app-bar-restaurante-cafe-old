package com.gallotti.appcafebarrestaurante.di

import com.gallotti.appcafebarrestaurante.repository.StoreRepository
import com.gallotti.appcafebarrestaurante.repository.StoreRepositoryContract
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {

    bean { StoreRepository(get()) as StoreRepositoryContract }

}
