package com.dev_marinov.nbadata

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// адаптер для фрагментов
class AdapterViewPager2(requireActivity: FragmentActivity, var fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(requireActivity) {

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun setData(fragments: ArrayList<Fragment>) {
        fragmentList = fragments
    }
}