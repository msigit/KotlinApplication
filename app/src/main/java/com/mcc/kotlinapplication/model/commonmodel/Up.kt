package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class Up(
    @SerializedName("embeddable")
    val embeddable: Boolean = false,
    @SerializedName("href")
    val href: String = "",
    @SerializedName("post_type")
    val postType: String = ""
)