package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("rendered")
    val rendered: String = ""
)