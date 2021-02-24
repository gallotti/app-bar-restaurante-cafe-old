package com.gallotti.appcafebarrestaurante.repository

import com.gallotti.appcafebarrestaurante.network.model.Store
import com.gallotti.appcafebarrestaurante.network.model.StoreDetail
import retrofit2.Response

interface StoreRepositoryContract{

    suspend fun  getStores (lat: Double, long:Double): Response<List<Store>>
    suspend fun  getDetailStore (id: Long): Response<StoreDetail>

}