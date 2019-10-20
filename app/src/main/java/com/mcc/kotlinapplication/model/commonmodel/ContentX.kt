package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class ContentX(
    @SerializedName("protected")
    val `protected`: Boolean = false,
    @SerializedName("rendered")
    val rendered: String = ""
)