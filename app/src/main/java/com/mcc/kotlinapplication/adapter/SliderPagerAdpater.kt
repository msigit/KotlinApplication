package com.mcc.kotlinapplication.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.listener.ItemClickListener
import com.mcc.kotlinapplication.model.postmodel.PostModel

class SliderPagerAdpater( private val arraylist: List<PostModel>) : PagerAdapter() {

    private var itemclickListener: ItemClickListener? = null

    fun setItemClickListener(itemclickListener: ItemClickListener) {
        this.itemclickListener = itemclickListener
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_slider_viewpager, container, false)

        val sliderImageView: ImageView = view.findViewById(R.id.slider_image)
        val titleTestView: TextView = view.findViewById(R.id.post_title)

        val postTitle = arraylist.get(position).title.rendered
        titleTestView.setText(Html.fromHtml(postTitle))

         val sliderImageUrl = arraylist.get(position).embedded.wpFeaturedmedia.get(0).sourceUrl;
        Glide.with(container.context).load(sliderImageUrl).into(sliderImageView)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return arraylist.size
    }
}