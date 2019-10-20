package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class Self(
    @SerializedName("href")
    val href: String = ""
)