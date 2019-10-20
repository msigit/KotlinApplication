package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class AvatarUrls(
    @SerializedName("24")
    val x24: String = "",
    @SerializedName("48")
    val x48: String = "",
    @SerializedName("96")
    val x96: String = ""
)