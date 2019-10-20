package com.mcc.kotlinapplication.constant

/**
 * Created by Sahidul Islam on 7/2/2019.
 */
class AppConstants {

    companion object {

        const val PAGE_NUMBER = 1
        const val PER_PAGE = 10

        const val EMPTY = " "

        const val BUNDLE_KEY_TITLE = "title"
        const val BUNDLE_KEY_MESSAGE = "message"
        const val BUNDLE_KEY_URL = "url"
        const val BUNDLE_FROM_PUSH = "from_push"
        const val BUNDLE_FROM_APP_LINK = "from_app_link"
        const val SITE_CACHE_SIZE = (10 * 1024 * 1024).toLong()
        const val BUNDLE_KEY_POST_ID = "post_id"
        const val BUNDLE_CATEGORY_ID = "category_id"
    }
}