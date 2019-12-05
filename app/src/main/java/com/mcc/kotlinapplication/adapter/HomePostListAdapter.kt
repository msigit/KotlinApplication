package com.mcc.kotlinapplication.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mcc.kotlinapplication.R
import com.mcc.kotlinapplication.listener.ItemClickListener
import com.mcc.kotlinapplication.model.postmodel.PostModel
import kotlinx.android.synthetic.main.item_post_list.view.*

/**
 * Created by Sahidul Islam on 7/2/2019.
 */
class HomePostListAdapter(val context: Context, val arraylist: ArrayList<PostModel>) :
    RecyclerView.Adapter<HomePostListAdapter.ViewHolder>() {

    lateinit var itemClickListener: ItemClickListener

    fun setOnItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_post_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = arraylist.get(position).embedded.wpFeaturedmedia.get(0).sourceUrl
        val postTitle = arraylist.get(position).title.rendered
        val postCategory = arraylist.get(position).embedded.wpTerm.get(0).get(0).name
        val postDate = arraylist.get(position).date
        val isFavourite = arraylist.get(position).isFavourite

        Glide.with(context).load(imageUrl).into(holder.itemView.post_img)
        holder.itemView.title_text?.text = Html.fromHtml(postTitle)
        holder.itemView.post_category?.text = postCategory
        holder.itemView.date_text?.text = postDate

        if (isFavourite) {
            holder.itemView.favourite_post.background =
                ContextCompat.getDrawable(context, R.drawable.ic_favorite_black_24dp)
        } else {
            holder.itemView.favourite_post.background =
                ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp)
        }

        holder.itemView.favourite_post.setColorFilter(
            PorterDuffColorFilter(
                ContextCompat.getColor(context, R.color.colorAccent),
                PorterDuff.Mode.SRC_IN
            )
        )
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.card_view.setOnClickListener(this)
            itemView.favourite_post.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener.onItemClick(adapterPosition, view)
        }
    }
}