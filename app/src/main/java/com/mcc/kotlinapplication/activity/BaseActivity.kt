package com.mcc.kotlinapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.database.helper.DaoHelper
import com.mcc.kotlinapplication.database.helper.DbLoaderInterface
import com.mcc.kotlinapplication.database.loader.FavouritePostLoader
import com.mcc.kotlinapplication.model.FavouritePostModel
import com.mcc.kotlinapplication.model.postmodel.PostModel

open class BaseActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }

    fun toggleFavourite(postModel: PostModel) {
        val favouriteLoader = FavouritePostLoader(applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
                val favouriteList = result as ArrayList<FavouritePostModel>
                if (favouriteList.size > 0) {
                    setFavourite(postModel)
                } else {
                    unsetFavourite(postModel.id)
                }
            }
        })
        favouriteLoader.execute(DaoHelper.FETCH, postModel.id)
    }

    fun setFavourite(postModel: PostModel) {
        val favouriteModel = FavouritePostModel(
            postModel.id,
            postModel.title.rendered,
            postModel.embedded.wpTerm.get(0).get(0).name,
            postModel.embedded.wpFeaturedmedia.get(0).sourceUrl,
            postModel.date
        )
        val favouriteLoader = FavouritePostLoader(applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
            }
        })
        favouriteLoader.execute(DaoHelper.INSERT_SINGLE, favouriteModel)
    }

    fun unsetFavourite(postId: Int) {
        val favouriteLoader = FavouritePostLoader(applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
            }
        })
        favouriteLoader.execute(DaoHelper.DELETE, postId)
    }
}

