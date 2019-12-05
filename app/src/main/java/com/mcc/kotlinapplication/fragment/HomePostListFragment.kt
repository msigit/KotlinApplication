package com.mcc.kotlinapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.adapter.HomePostListAdapter
import com.mcc.kotlinapplication.constant.AppConstants
import com.mcc.kotlinapplication.database.helper.DaoHelper
import com.mcc.kotlinapplication.database.helper.DbLoaderInterface
import com.mcc.kotlinapplication.database.loader.FavouritePostLoader
import com.mcc.kotlinapplication.listener.ItemClickListener
import com.mcc.kotlinapplication.model.FavouritePostModel
import com.mcc.kotlinapplication.model.postmodel.PostModel
import com.mcc.kotlinapplication.network.ApiClient
import com.mcc.kotlinapplication.network.ApiRequest
import com.mcc.kotlinapplication.progress.SquareDotsLoadingView
import com.mcc.kotlinapplication.progress.WindowsLoadingView
import com.mcc.kotlinapplication.utils.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Sahidul Islam on 7/2/2019.
 */
class HomePostListFragment : Fragment() {

    private var homePostAdapter: HomePostListAdapter? = null

    private var postArrayList = ArrayList<PostModel>()
    private var categoryId: String = ""
    private var canLoadMore: Boolean = false
    private var isDataLoading: Boolean = false

    private var pageNumber: Int = AppConstants.PAGE_NUMBER

    private var postRecyclerview: RecyclerView? = null
    private var nestedScrollView: NestedScrollView? = null
    private var progressView: SquareDotsLoadingView? = null
    private var moreProgressView: WindowsLoadingView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_post_list, container, false)

        postRecyclerview = view.findViewById<RecyclerView>(R.id.post_recycler)
        nestedScrollView = view.findViewById<NestedScrollView>(R.id.nested_scroll_view)
        progressView = view.findViewById<SquareDotsLoadingView>(R.id.loader_progress)
        moreProgressView = view.findViewById<WindowsLoadingView>(R.id.load_more_progress)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initListener()

        loadCategoryPost(AppConstants.PAGE_NUMBER)
    }

    private fun initVar() {
        val bundle: Bundle? = arguments
        categoryId = bundle!!.getString(AppConstants.BUNDLE_CATEGORY_ID).toString()
    }

    private fun initListener() {
        homePostAdapter?.setOnItemClickListener(object : ItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                if (view.id == R.id.card_view) {
                } else if (view.id == R.id.favourite_post) {
                    val postModel = postArrayList[position]
                    toggleFavourite(position, postModel)
                }
            }
        })
    }

    private fun initRecyclerView() {
        homePostAdapter = HomePostListAdapter(activity?.applicationContext!!, postArrayList)
        postRecyclerview?.layoutManager = GridLayoutManager(activity?.applicationContext!!, 2)
        postRecyclerview?.isNestedScrollingEnabled = false
        postRecyclerview?.adapter = homePostAdapter

        val onScrollchange =
            NestedScrollView.OnScrollChangeListener { scrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > 0) {
                    if (!scrollView?.canScrollVertically(NestedScrollView.FOCUS_DOWN)!!) {
                        if (canLoadMore && !isDataLoading) {
                            isDataLoading = true;
                            moreProgressView?.setVisibility(View.VISIBLE);
                            pageNumber += 1;
                        }
                    }
                }
            }

        nestedScrollView?.setOnScrollChangeListener(onScrollchange)
    }

    private fun loadCategoryPost(pageNumber: Int) {
        if (Utility().isNetworkAvailable(activity?.applicationContext!!)) {
            progressView?.visibility = View.VISIBLE
            val requestMap: HashMap<String, String> =
                ApiRequest.buildCategoryPostRequest(categoryId, pageNumber)
            val call: Call<List<PostModel>> = ApiClient.getClient.getPosts(requestMap)

            call.enqueue(object : Callback<List<PostModel>> {
                override fun onResponse(
                    call: Call<List<PostModel>>?,
                    response: Response<List<PostModel>>?
                ) {
                    if (response!!.isSuccessful) {
                        postArrayList.addAll(response.body()!!)

                        showCategoryPost()
                        postRecyclerview?.visibility = View.VISIBLE
                    }
                    progressView?.visibility = View.GONE
                }

                override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
                    progressView?.visibility = View.GONE
                }
            })
        }
    }

    private fun showCategoryPost() {
        val favouriteLoader = FavouritePostLoader(activity!!.applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
                val favouriteList = result as ArrayList<FavouritePostModel>

                for (favouriteModel: FavouritePostModel in favouriteList) {
                    for (postModel: PostModel in postArrayList) {
                        if (favouriteModel.postId == postModel.id) {
                            postModel.isFavourite = true
                        }
                    }
                }
                homePostAdapter?.notifyDataSetChanged()
            }
        })
        favouriteLoader.execute(DaoHelper.FETCH_ALL)
    }

    private fun toggleFavourite(position: Int, postModel: PostModel) {
        val favouriteLoader = FavouritePostLoader(activity!!.applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
                val favouriteList = result as ArrayList<FavouritePostModel>
                if (favouriteList.size > 0) {
                    unsetFavourite(postModel.id)
                    postModel.isFavourite = false
                } else {
                    setFavourite(postModel)
                    postModel.isFavourite = true
                }

                postArrayList[position] = postModel
                homePostAdapter?.notifyItemChanged(position)
            }
        })
        favouriteLoader.execute(DaoHelper.FETCH, postModel.id)
    }

    private fun setFavourite(postModel: PostModel) {
        val favouriteModel = FavouritePostModel(
            postModel.id,
            postModel.title.rendered,
            postModel.embedded.wpTerm.get(0).get(0).name,
            postModel.embedded.wpFeaturedmedia.get(0).sourceUrl,
            postModel.date
        )
        val favouriteLoader = FavouritePostLoader(activity!!.applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
            }
        })
        favouriteLoader.execute(DaoHelper.INSERT_SINGLE, favouriteModel)
    }

    private fun unsetFavourite(postId: Int) {
        val favouriteLoader = FavouritePostLoader(activity!!.applicationContext)
        favouriteLoader.setDbLoaderInterface(object : DbLoaderInterface {
            override fun onFinished(result: Any) {
            }
        })
        favouriteLoader.execute(DaoHelper.DELETE, postId)
    }
}