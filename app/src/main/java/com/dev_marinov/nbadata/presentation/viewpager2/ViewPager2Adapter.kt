package com.dev_marinov.nbadata.presentation.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter(requireActivity: FragmentActivity, var fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(requireActivity) {

//    var fragmentList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun setData(fragments: ArrayList<Fragment>) {
        fragmentList = fragments
    }
}