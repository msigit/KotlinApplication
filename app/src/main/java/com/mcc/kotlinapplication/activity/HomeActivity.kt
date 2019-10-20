package com.mcc.kotlinapplication.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.adapater.HomePagerAdapter
import com.mcc.kotlinapplication.adapater.SliderPagerAdpater
import com.mcc.kotlinapplication.databinding.ActivityHomeBinding
import com.mcc.kotlinapplication.model.CategoryModel
import com.mcc.kotlinapplication.model.postmodel.PostModel
import com.mcc.kotlinapplication.network.ApiClient
import com.mcc.kotlinapplication.network.ApiRequest
import com.mcc.kotlinapplication.progress.SquareDotsLoadingView
import com.mcc.kotlinapplication.utils.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener
{

    private var mActivity: Activity? = null
    private var mContext: Context? = null

    private lateinit var binding: ActivityHomeBinding

    private var sliderPagerAdapter: SliderPagerAdpater? = null
    private var homePagerAdapter: HomePagerAdapter? = null

    private var sliderArrayList = ArrayList<PostModel>()
    private var categoryArrayList = ArrayList<CategoryModel>()

    private var drawer: DrawerLayout? = null
    private var navigation: NavigationView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var tablayout: TabLayout? = null
    private var sliderViewPager: ViewPager? = null
    private var contentViewPager: ViewPager? = null
    private var sliderProgress: SquareDotsLoadingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVar()
        initView()
        initListener()
        initFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_toolbar_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.menu_search -> {
                return true
            }
            R.id.menu_notification -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            R.id.menu_favourite -> {
                val intent = Intent(this, MyFavouriteActivity::class.java)
                startActivity(intent)
            }
        }

        item.isChecked = true
        drawer?.closeDrawer(GravityCompat.START)

        return true
    }

    private fun initVar() {
        mActivity = this@HomeActivity
        mContext = applicationContext
    }

    private fun initView() {
        //setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)

        drawer = findViewById(R.id.drawerlayout);
        navigation = findViewById(R.id.navigationView);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        tablayout = findViewById(R.id.tablayout);
        sliderViewPager = findViewById(R.id.slider_viewpager);
        contentViewPager = findViewById(R.id.content_viewpager);
        sliderProgress = findViewById(R.id.slider_progress);

        initToolbar()
        initDrawerLayout()
        initSliderLayout()
        initTablayout()
    }

    private fun initListener() {
        contentViewPager?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                toggleEnableSwipeRefresh(position == ViewPager.SCROLL_STATE_IDLE)
            }
        })
    }

    private fun initFunctionality() {
        loadSliderPosts()
        loadPostCategories()
    }

    private fun initDrawerLayout() {
        navigation?.setNavigationItemSelectedListener(this)
        navigation?.menu?.getItem(0)?.setChecked(true)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_open,
            R.string.navigation_close
        )
        drawer?.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initSliderLayout() {
        sliderPagerAdapter = SliderPagerAdpater(sliderArrayList)
        sliderViewPager?.adapter = sliderPagerAdapter

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            var setPosition = sliderViewPager?.getCurrentItem()?.plus(1)
            if (setPosition == sliderArrayList.size) {
                setPosition = 0
            }
            sliderViewPager?.setCurrentItem(setPosition!!, true)
        }

        //  Auto animated timer
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)
    }

    private fun initTablayout() {
        homePagerAdapter = HomePagerAdapter(supportFragmentManager, categoryArrayList)
        contentViewPager?.adapter = homePagerAdapter
        tablayout?.setupWithViewPager(contentViewPager)
    }

    private fun loadSliderPosts() {
        if (Utility().isNetworkAvailable(applicationContext)) {
            sliderProgress?.visibility = View.VISIBLE
            val requestMap: HashMap<String, String> = ApiRequest.buildPostRequest(false, 5, 2)
            val call: Call<List<PostModel>> = ApiClient.getClient.getPosts(requestMap)

            call.enqueue(object : Callback<List<PostModel>> {
                override fun onResponse(
                    call: Call<List<PostModel>>?,
                    response: Response<List<PostModel>>?
                ) {
                    if (response!!.isSuccessful) {
                        sliderArrayList.addAll(response.body()!!)
                        sliderPagerAdapter?.notifyDataSetChanged()
                        sliderViewPager?.visibility = View.VISIBLE
                    }
                    sliderProgress?.visibility = View.GONE
                }

                override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
                    sliderProgress?.visibility = View.GONE
                }
            })
        }
    }

    private fun loadPostCategories() {
        if (Utility().isNetworkAvailable(applicationContext)) {
            sliderProgress?.visibility = View.VISIBLE
            val requestMap: HashMap<String, String> = ApiRequest.buildCategoryRequest(20)
            val call: Call<List<CategoryModel>> = ApiClient.getClient.getCategories(requestMap)

            call.enqueue(object : Callback<List<CategoryModel>> {
                override fun onResponse(
                    call: Call<List<CategoryModel>>?,
                    response: Response<List<CategoryModel>>?
                ) {
                    if (response!!.isSuccessful) {
                        categoryArrayList.addAll(response.body()!!)

                        if (categoryArrayList.size > 0) {
                            homePagerAdapter?.notifyDataSetChanged();

                            tablayout?.setVisibility(View.VISIBLE);
                            contentViewPager?.setVisibility(View.VISIBLE);
                        }

                        sliderViewPager?.visibility = View.VISIBLE
                    }
                    sliderProgress?.visibility = View.GONE
                }

                override fun onFailure(call: Call<List<CategoryModel>>?, t: Throwable?) {
                    //  sliderProgress?.visibility = View.GONE
                }
            })
        }
    }

    private fun toggleEnableSwipeRefresh(enable: Boolean) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout?.setEnabled(enable)
        }
    }
}
