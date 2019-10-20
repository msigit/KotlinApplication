package com.mcc.kotlinapplication.network

import com.mcc.kotlinapplication.model.CategoryModel
import com.mcc.kotlinapplication.model.postmodel.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * Created by Sahidul Islam on 6/30/2019.
 */
interface ApiInterface {

    @GET(HttpParams.API_POSTS)
    fun getPosts(@QueryMap hashMap: HashMap<String, String>): Call<List<PostModel>>

    @GET(HttpParams.API_CATEGORIES)
    fun getCategories(@QueryMap hashMap: HashMap<String, String>): Call<List<CategoryModel>>

}