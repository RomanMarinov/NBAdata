package com.dev_marinov.nbadata.presentation.viewpager2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import android.view.animation.Animation

import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.FragmentTabViewPager2Binding
import com.dev_marinov.nbadata.presentation.games.GamesFragment
import com.dev_marinov.nbadata.presentation.players.PlayersFragment
import com.dev_marinov.nbadata.presentation.teams.TeamsFragment

class ViewPager2Fragment : Fragment() {

    private lateinit var binding: FragmentTabViewPager2Binding
    lateinit var viewPager2ViewModel: ViewPager2ViewModel

    var START_POS_X: Float? = null
    var END_POS_X: Float? = null
    val START_POS_Y: Float = 0F
    val END_POS_Y: Float = 0F
    val TICKER_DURATION: Long = 5000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        return initInterFace(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAnimation()

    }

    private fun initInterFace(inflater: LayoutInflater, container: ViewGroup?): View {
            container?.let { container.removeAllViewsInLayout() }

            // получить экран ориентации
            val orientation = requireActivity().resources.configuration.orientation
            // раздуть соответствующий макет в зависимости от ориентации экрана
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_view_pager2, container, false)
                START_POS_X = -800F
                END_POS_X = 800F
            } else {
                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_view_pager2, container, false)
                START_POS_X = -1200F
                END_POS_X = 1200F
            }

            setViewPager2Adapter()

            return binding.root
        }



        private fun setViewPager2Adapter() {

            viewPager2ViewModel = ViewModelProvider(this)[ViewPager2ViewModel::class.java]

            val adapterViewPager2 = ViewPager2Adapter(requireActivity(), createListFragments())
            adapterViewPager2.setData(createListFragments())
            binding.viewPager2.adapter = adapterViewPager2

            // тут надо установить наблюдатель для viewPager2ViewModel.lastTab
//            viewPager2ViewModel.lastTab.


            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(viewPager2ViewModel.lastTab))
            binding.viewPager2.currentItem = viewPager2ViewModel.lastTab

            setTabLayoutMediator(addHeadlinesTab())
            setViewPager2()
        }

    // устанавливаем заголовки для табов
    private fun addHeadlinesTab() : ArrayList<String>{
        val headlinesTab: ArrayList<String> = ArrayList()
        headlinesTab.add(resources.getString(R.string.tab_Players))
        headlinesTab.add(resources.getString(R.string.tab_Teams))
        headlinesTab.add(resources.getString(R.string.tab_Games))
        return headlinesTab
    }

    private fun createListFragments() : ArrayList<Fragment> {
        val fragmentList: ArrayList<Fragment> = ArrayList()
        fragmentList.add(PlayersFragment())
        fragmentList.add(TeamsFragment())
        fragmentList.add(GamesFragment())
        return fragmentList
    }

    // TabLayoutMediator для синхронизации компонента TabLayout с ViewPager2
    // установка текста заголовков вкладок, стиля вкладок
    private fun setTabLayoutMediator(titleTab: ArrayList<String>) {
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titleTab[position]
        }.attach()
    }

    private fun setViewPager2() {
        binding.viewPager2.offscreenPageLimit = 3

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                viewPager2ViewModel.lastTab = position
                Log.e("333", " onPageSelected position$position")
                super.onPageSelected(position)
            }
            override fun onPageScrollStateChanged(state: Int) {
                //tabLayout.selectTab(tabLayout.getTabAt(viewModelTabViewPager2.lastTab))
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun setAnimation(){
        val animation: Animation = TranslateAnimation(
            START_POS_X!!, END_POS_X!!,
            START_POS_Y, END_POS_Y
        )
        animation.duration = TICKER_DURATION
        animation.repeatMode = Animation.RESTART
        animation.repeatCount = Animation.INFINITE

        binding.tvTitle.animation = animation
    }
}