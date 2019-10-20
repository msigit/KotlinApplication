package com.mcc.kotlinapplication.adapater

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mcc.kotlinapplication.constant.AppConstants
import com.mcc.kotlinapplication.fragment.HomePostListFragment
import com.mcc.kotlinapplication.model.CategoryModel

/**
 * Created by Sahidul Islam on 7/2/2019.
 */
class HomePagerAdapter(fm: FragmentManager, private val arraylist: List<CategoryModel>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val categoryId = arraylist.get(position).id

        val postListFragment = HomePostListFragment()
        val args = Bundle()
        args.putString(AppConstants.BUNDLE_CATEGORY_ID, categoryId.toString())
        postListFragment.setArguments(args)
        return postListFragment
    }

    override fun getCount(): Int {
        return  arraylist.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Html.fromHtml(arraylist.get(position).name)
    }
}