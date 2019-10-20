package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.postmodel.Full

data class Sizes(
    @SerializedName("full")
    val full: Full = Full(),
    @SerializedName("medium")
    val medium: Medium = Medium(),
    @SerializedName("medium_large")
    val mediumLarge: MediumLarge = MediumLarge(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("twentyseventeen-thumbnail-avatar")
    val twentyseventeenThumbnailAvatar: TwentyseventeenThumbnailAvatar = TwentyseventeenThumbnailAvatar()
)