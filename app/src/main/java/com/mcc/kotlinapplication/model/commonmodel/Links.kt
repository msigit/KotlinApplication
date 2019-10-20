package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("collection")
    val collection: List<Collection> = listOf(),
    @SerializedName("self")
    val self: List<Self> = listOf()
)