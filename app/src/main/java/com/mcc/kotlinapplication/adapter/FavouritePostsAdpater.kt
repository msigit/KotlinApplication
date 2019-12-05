package com.mcc.kotlinapplication.adapter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.listener.ItemClickListener
import com.mcc.kotlinapplication.model.FavouritePostModel
import kotlinx.android.synthetic.main.item_favourite_post_list.view.*

/**
 * Created by Sahidul Islam on 10/2/2019.
 */
class FavouritePostsAdpater(val context: Context, val arrayList: ArrayList<FavouritePostModel>) :
    RecyclerView.Adapter<FavouritePostsAdpater.ViewHolder>() {

    lateinit var itemClickListener: ItemClickListener

    fun setOnItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_favourite_post_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = arrayList.get(position).postImage
        val postTitle = arrayList.get(position).postTitle
        val postCategory = arrayList.get(position).postCategory
        val postDate = arrayList.get(position).postDate

        Glide.with(context).load(imageUrl).into(holder.itemView.post_img)
        holder.itemView.title_text?.text = Html.fromHtml(postTitle, Html.FROM_HTML_MODE_LEGACY)
        holder.itemView.post_category?.text = postCategory
        holder.itemView.date_text?.text = postDate
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.remove_favourite.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener.onItemClick(adapterPosition, view)
        }
    }
}