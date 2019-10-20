package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class Guid(
    @SerializedName("rendered")
    val rendered: String = ""
)