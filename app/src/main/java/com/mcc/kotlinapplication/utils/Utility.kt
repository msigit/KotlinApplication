package com.mcc.kotlinapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.mcc.kotlinapplication.R

/**
 * Created by Sahidul Islam on 7/1/2019.
 */
class Utility {

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(view: View, context: Context, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(context.getString(R.string.ok), View.OnClickListener { })
        val snackbarView = snackbar.getView()
        val snackTextView = snackbarView.findViewById(R.id.snackbar_text) as TextView
        snackTextView.maxLines = 5
        snackbar.show()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}