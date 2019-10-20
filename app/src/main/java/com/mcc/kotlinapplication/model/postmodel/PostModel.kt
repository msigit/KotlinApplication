package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.commonmodel.Content
import com.mcc.kotlinapplication.model.commonmodel.Embedded
import com.mcc.kotlinapplication.model.commonmodel.Title

data class PostModel(
    @SerializedName("author")
    val author: Int = 0,
    @SerializedName("categories")
    val categories: List<Int> = listOf(),
    @SerializedName("comment_status")
    val commentStatus: String = "",
    @SerializedName("content")
    val content: Content = Content(),
    @SerializedName("date")
    val date: String = "",
    @SerializedName("date_gmt")
    val dateGmt: String = "",
    @SerializedName("_embedded")
    val embedded: Embedded = Embedded(),
    @SerializedName("excerpt")
    val excerpt: Excerpt = Excerpt(),
    @SerializedName("featured_media")
    val featuredMedia: Int = 0,
    @SerializedName("format")
    val format: String = "",
    @SerializedName("guid")
    val guid: Guid = Guid(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("meta")
    val meta: List<Any> = listOf(),
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("modified_gmt")
    val modifiedGmt: String = "",
    @SerializedName("ping_status")
    val pingStatus: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sticky")
    val sticky: Boolean = false,
    @SerializedName("template")
    val template: String = "",
    @SerializedName("title")
    val title: Title = Title(),
    @SerializedName("type")
    val type: String = "",

    var isFavourite: Boolean = false
)