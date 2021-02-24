package com.gallotti.appcafebarrestaurante.extensions

import java.io.Serializable

inline fun <reified T : Serializable> androidx.fragment.app.Fragment.serializableArgument(key: String): T? =
    this.arguments?.getSerializable(key) as T