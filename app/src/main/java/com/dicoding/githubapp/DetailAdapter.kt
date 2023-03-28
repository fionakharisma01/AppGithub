package com.dicoding.githubapp

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DetailAdapter(private val mCtx: Context, fm: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)

    private var fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    override fun getCount(): Int = TAB.size

    override fun getItem(position: Int): Fragment {
        var frag: Fragment? = null
        when (position) {
            0 -> frag = FragmentFollowers()
            1 -> frag = FragmentFollowing()
        }
        frag?.arguments = this.fragmentBundle
        return frag as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mCtx.resources.getString(TAB[position])
    }
}