package com.mcc.kotlinapplication.database.loader

import android.content.Context
import android.os.AsyncTask
import com.mcc.kotlinapplication.database.helper.AppDatabase
import com.mcc.kotlinapplication.database.helper.DaoHelper
import com.mcc.kotlinapplication.database.helper.DbLoaderInterface
import com.mcc.kotlinapplication.model.FavouritePostModel

/**
 * Created by Sahidul Islam on 9/26/2019.
 */
class FavouritePostLoader(val context: Context) : AsyncTask<Any, Void, Any>() {

    private lateinit var dbLoaderInterface: DbLoaderInterface

    fun setDbLoaderInterface(loaderInterface: DbLoaderInterface) {
        dbLoaderInterface = loaderInterface
    }

    override fun doInBackground(vararg params: Any?): Any {
        val crud = params[0]
        val db = AppDatabase.invoke(context)

        when (crud) {
            DaoHelper.INSERT_SINGLE -> {
                val favouriteModel: FavouritePostModel = params[1] as FavouritePostModel
                db.FavouritePostDao().insert(favouriteModel)
            }
            DaoHelper.FETCH_ALL -> {
                return db.FavouritePostDao().getAll()
            }
            DaoHelper.FETCH -> {
                return db.FavouritePostDao().getPost(params[1] as Int)
            }
            DaoHelper.DELETE -> {
                db.FavouritePostDao().deletePost(params[1] as Int)
            }
        }

        return params
    }

    override fun onPostExecute(result: Any) {
        super.onPostExecute(result)
        if (::dbLoaderInterface.isInitialized) {
            dbLoaderInterface.onFinished(result)
        }
    }
}