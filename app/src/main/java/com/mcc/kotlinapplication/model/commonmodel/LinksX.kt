package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("collection")
    val collection: List<CollectionX> = listOf(),
    @SerializedName("self")
    val self: List<SelfX> = listOf(),
    @SerializedName("up")
    val up: List<Up> = listOf()
)