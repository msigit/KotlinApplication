package com.mcc.kotlinapplication.model.postmodel


import com.google.gson.annotations.SerializedName

data class WpTerm(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("link")
    val link: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("taxonomy")
    val taxonomy: String = ""
)