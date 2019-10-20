package com.mcc.kotlinapplication.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.adapater.FavouritePostsAdpater
import com.mcc.kotlinapplication.database.helper.DaoHelper
import com.mcc.kotlinapplication.database.helper.DbLoaderInterface
import com.mcc.kotlinapplication.database.loader.FavouritePostLoader
import com.mcc.kotlinapplication.listener.ItemClickListener
import com.mcc.kotlinapplication.model.FavouritePostModel
import kotlinx.android.synthetic.main.activity_my_favourite.*


/**
 * Created by Sahidul Islam on 9/26/2019.
 */
class MyFavouriteActivity : BaseActivity() {

    private var mActivity: Activity? = null
    private var mContext: Context? = null

    private lateinit var favouritePostsAdpater: FavouritePostsAdpater

    private lateinit var favArrayList: ArrayList<FavouritePostModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVar()
        initView()
        initFunctionality()
        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initVar() {
        mActivity = this@MyFavouriteActivity
        mContext = applicationContext

        favArrayList = ArrayList<FavouritePostModel>()
    }

    private fun initView() {
        setContentView(R.layout.activity_my_favourite)
        initToolbar()
    }

    private fun initListener() {
        favouritePostsAdpater.setOnItemClickListener(object : ItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                if (view.id == R.id.remove_favourite) {
                    val postModel = favArrayList[position]
                    unsetFavourite(postModel.postId)
                    favArrayList.removeAt(position)
                    favouritePostsAdpater.notifyItemRemoved(position)
                }
            }
        })
    }

    private fun initFunctionality() {
        initRecyclerView()
        favouriteList()
    }

    private fun initRecyclerView() {
        favouritePostsAdpater = FavouritePostsAdpater(mContext!!, favArrayList)
        recycler_view?.layoutManager = GridLayoutManager(mContext, 2)
        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = favouritePostsAdpater
    }

    private fun favouriteList() {
        val favouriteLoader = FavouritePostLoader(mActivity!!.applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
                val arrayList: ArrayList<FavouritePostModel> =
                    result as ArrayList<FavouritePostModel>

                favArrayList.addAll(arrayList)
                favouritePostsAdpater.notifyDataSetChanged()
            }
        })
        favouriteLoader.execute(DaoHelper.FETCH_ALL)
    }
}