package com.mcc.kotlinapplication.model.commonmodel


import com.google.gson.annotations.SerializedName
import com.mcc.kotlinapplication.model.postmodel.Author
import com.mcc.kotlinapplication.model.postmodel.Reply
import com.mcc.kotlinapplication.model.postmodel.WpFeaturedmedia
import com.mcc.kotlinapplication.model.postmodel.WpTerm

data class Embedded(
    @SerializedName("author")
    val author: List<Author> = listOf(),
    @SerializedName("replies")
    val replies: List<List<Reply>> = listOf(),
    @SerializedName("wp:featuredmedia")
    val wpFeaturedmedia: List<WpFeaturedmedia> = listOf(),
    @SerializedName("wp:term")
    val wpTerm: List<List<WpTerm>> = listOf()
)