package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName

data class MediaDetails(
    @SerializedName("file")
    val `file`: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("image_meta")
    val imageMeta: ImageMeta = ImageMeta(),
    @SerializedName("sizes")
    val sizes: Sizes = Sizes(),
    @SerializedName("width")
    val width: Int = 0
)