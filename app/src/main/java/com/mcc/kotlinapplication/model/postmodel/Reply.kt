package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.commonmodel.Content
import com.mcc.kotlinapplication.model.commonmodel.LinksX

data class Reply(
    @SerializedName("author")
    val author: Int = 0,
    @SerializedName("author_avatar_urls")
    val authorAvatarUrls: AuthorAvatarUrls = AuthorAvatarUrls(),
    @SerializedName("author_name")
    val authorName: String = "",
    @SerializedName("author_url")
    val authorUrl: String = "",
    @SerializedName("content")
    val content: Content = Content(),
    @SerializedName("date")
    val date: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("_links")
    val links: LinksX = LinksX(),
    @SerializedName("parent")
    val parent: Int = 0,
    @SerializedName("type")
    val type: String = ""
)