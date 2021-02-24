package com.gallotti.appcafebarrestaurante.repository

import com.gallotti.appcafebarrestaurante.network.api.StoresApi
import com.gallotti.appcafebarrestaurante.network.model.Store
import com.gallotti.appcafebarrestaurante.network.model.StoreDetail
import retrofit2.Response

class StoreRepository(
    private var storeApi: StoresApi
): StoreRepositoryContract{

    override suspend fun getStores(lat: Double, long:Double): Response<List<Store>> {
       return storeApi.getListStore(lat, long)
    }

    override suspend fun getDetailStore(id: Long): Response<StoreDetail> {
        return storeApi.getDetailStore(id)
    }

}