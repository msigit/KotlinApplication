package com.mcc.kotlinapplication.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.databinding.ActivitySplashBinding


class SplashActivity : BaseActivity() {

    private var mActivity: Activity? = null
    private var mContext: Context? = null

    private lateinit var binding: ActivitySplashBinding

    private val SPLASH_DURATION: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVar()
        initView()
        initFunctionality()
    }

    private fun initVar() {
        mActivity = this@SplashActivity
        mContext = applicationContext
    }

    private fun initView() {
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //  setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)
    }

    private fun initFunctionality() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, SPLASH_DURATION)
    }

}