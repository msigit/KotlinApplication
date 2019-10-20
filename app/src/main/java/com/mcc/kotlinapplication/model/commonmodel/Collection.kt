package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("href")
    val href: String = ""
)