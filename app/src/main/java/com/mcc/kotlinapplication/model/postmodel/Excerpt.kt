package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class Excerpt(
    @SerializedName("protected")
    val `protected`: Boolean = false,
    @SerializedName("rendered")
    val rendered: String = ""
)