package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class Full(
    @SerializedName("file")
    val `file`: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("mime_type")
    val mimeType: String = "",
    @SerializedName("source_url")
    val sourceUrl: String = "",
    @SerializedName("width")
    val width: Int = 0
)