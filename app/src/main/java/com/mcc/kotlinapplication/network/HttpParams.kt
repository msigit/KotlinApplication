package com.mcc.kotlinapplication.network

/**
 * Created by Sahidul Islam on 6/30/2019.
 */
class HttpParams {

    companion object {

        const val APP_URL: String = "http://mccltd.info/projects/photographic-portfolio/"
        const val API_URL: String = "wp-json/wp/v2/"
        const val BASE_URL: String = APP_URL + API_URL


        const val KEY_PER_PAGE = "per_page"
        const val KEY_PAGE = "page"
        const val KEY_CATEGORIES = "categories"
        const val KEY_MAXIMUM_ITEM_PER_PAGE = "20"
        const val KEY_ITEM_PER_PAGE = "10"
        const val KEY_ID = "id"
        const val KEY_EMBED = "_embed"
        const val KEY_FEATURED = "sticky=true"
        const val KEY_POST = "post"
        const val KEY_PARENT = "parent"
        const val KEY_SEARCH = "search"


        const val API_CATEGORIES = BASE_URL + "categories"
        const val API_POSTS = BASE_URL + "posts"
    }

}