package com.gallotti.appcafebarrestaurante.network.api

import com.gallotti.appcafebarrestaurante.network.model.Store
import com.gallotti.appcafebarrestaurante.network.model.StoreDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoresApi {
    @GET("buscar/{lat}/{long}")
    suspend fun getListStore(
        @Path("lat") latitude: Double,
        @Path("long") longintude: Double): Response<List<Store>>


    @GET("detail/{id}")
    suspend fun getDetailStore(
        @Path("id") id: Long): Response<StoreDetail>
}