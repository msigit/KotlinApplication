package com.mcc.kotlinapplication.listener

import android.view.View

interface ItemClickListener {
    fun onItemClick(position: Int, view: View)
}