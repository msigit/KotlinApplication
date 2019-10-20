package com.mcc.kotlinapplication.network

import com.mcc.kotlinapplication.constant.AppConstants


/**
 * Created by Sahidul Islam on 6/30/2019.
 */
object ApiRequest {

    fun buildPostRequest(featured: Boolean, perPage: Int, pageNo: Int): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap<String, String>()
        if (featured) hashMap.put(HttpParams.KEY_FEATURED, "true")
        hashMap.put(HttpParams.KEY_EMBED, "true")
        hashMap.put(HttpParams.KEY_PER_PAGE, perPage.toString())
        hashMap.put(HttpParams.KEY_PAGE, pageNo.toString())

        return hashMap
    }

    fun buildCategoryPostRequest(categories: String, pageNo: Int): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap<String, String>()
        hashMap.put(HttpParams.KEY_EMBED, "true")
        hashMap.put(HttpParams.KEY_CATEGORIES, categories)
        hashMap.put(HttpParams.KEY_PER_PAGE, AppConstants.PER_PAGE.toString())
        hashMap.put(HttpParams.KEY_PAGE, pageNo.toString())

        return hashMap
    }

    fun buildCategoryRequest(perPage: Int): HashMap<String, String> {
        val hashMap: HashMap<String, String> = HashMap<String, String>()
        hashMap.put(HttpParams.KEY_EMBED, "true")
        hashMap.put(HttpParams.KEY_PER_PAGE, perPage.toString())

        return hashMap
    }
}