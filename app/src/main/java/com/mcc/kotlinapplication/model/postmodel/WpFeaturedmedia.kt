package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.commonmodel.MediaDetails
import com.mcc.kotlinapplication.model.commonmodel.Title

data class WpFeaturedmedia(
    @SerializedName("alt_text")
    val altText: String = "",
    @SerializedName("author")
    val author: Int = 0,
    @SerializedName("caption")
    val caption: Caption = Caption(),
    @SerializedName("date")
    val date: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("media_details")
    val mediaDetails: MediaDetails = MediaDetails(),
    @SerializedName("media_type")
    val mediaType: String = "",
    @SerializedName("mime_type")
    val mimeType: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("source_url")
    val sourceUrl: String = "",
    @SerializedName("title")
    val title: Title = Title(),
    @SerializedName("type")
    val type: String = ""
)