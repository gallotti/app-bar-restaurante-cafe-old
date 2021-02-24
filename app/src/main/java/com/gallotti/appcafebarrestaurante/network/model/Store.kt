package com.gallotti.appcafebarrestaurante.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Store(
    @Json(name = "id")
    val id : Long ,

    @Json(name = "address")
    val address : Address ,

    @Json(name = "name")
    val comboImeis: String
) : Serializable

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "state")
    val state : String ,

    @Json(name = "city")
    val city: String,

    @Json(name = "complement")
    var complement: String
)

@JsonClass(generateAdapter = true)
data class StoreDetail(
    @Json(name = "id")
    val id : Long ,

    @Json(name = "imagesUrl")
    val images : List<String> ,

    @Json(name = "desc")
    val desc: String
)
