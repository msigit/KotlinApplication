package com.mcc.kotlinapplication.model


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("parent")
    val parent: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("taxonomy")
    val taxonomy: String = ""
)