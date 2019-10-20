package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.commonmodel.Links

data class Author(
    @SerializedName("avatar_urls")
    val avatarUrls: AvatarUrls = AvatarUrls(),
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("_links")
    val links: Links = Links(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("url")
    val url: String = ""
)