package com.dicoding.submission2

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FollowersFollowingAdapter(private val fol:Context, fm: FragmentManager,data:Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle:Bundle
    init {
        fragmentBundle =data
    }

    @StringRes
    private val tab = intArrayOf(R.string.tab_text_1,R.string.tab_text_2)
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment?=null
        when(position){
            0->fragment = FollowersFragment()
            1->fragment = FollowingFragment()
        }
        fragment?.arguments =this.fragmentBundle
        return fragment as Fragment
    }
    override fun getPageTitle(position: Int): CharSequence {
        return fol.resources.getString(tab[position])
    }
}