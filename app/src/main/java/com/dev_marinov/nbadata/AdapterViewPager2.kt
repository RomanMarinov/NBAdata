package com.dev_marinov.nbadata

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager2(requireActivity: FragmentActivity, var fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(requireActivity) {

    override fun createFragment(position: Int): Fragment {
        //Log.e("444","ViewPager2Adapter createFragment position=" + position);
        //Log.e("444","ViewPager2Adapter createFragment fragments.get(position)=" + fragments.get(position));
        return fragmentList.get(position)
    }

    override fun getItemCount(): Int {
        //Log.e("444","ViewPager2Adapter fragments.size()" + fragments.size());
        return fragmentList.size
    }

    fun setData(fragments: ArrayList<Fragment>) {
        fragmentList = fragments
        //Log.e("444","ViewPager2Adapter вызвался setData fragments" + fragments.size());
    }
}