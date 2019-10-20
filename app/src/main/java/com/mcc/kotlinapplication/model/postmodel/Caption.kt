package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class Caption(
    @SerializedName("rendered")
    val rendered: String = ""
)