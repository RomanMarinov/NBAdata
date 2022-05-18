package com.dev_marinov.nbadata.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.view.animation.Animation

import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dev_marinov.nbadata.R

class FragmentTabViewPager2 : Fragment() {

    lateinit var viewModelTabViewPager2: ViewModelTabViewPager2

    var START_POS_X: Float? = null
    var END_POS_X: Float? = null
    val START_POS_Y: Float = 0F
    val END_POS_Y: Float = 0F
    val TICKER_DURATION: Long = 5000

    lateinit var tvTitle: TextView
    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var titleTab: ArrayList<String> // массив названий табов заголовков
    lateinit var fragmentList: ArrayList<Fragment> // массив табов фрагментов

    var viewGroupTabViewPager2: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewGroupTabViewPager2 = container
        return initInterface()
    }
        fun initInterface(): View? {
            val view: View
            if (viewGroupTabViewPager2 != null) {
                viewGroupTabViewPager2!!.removeAllViewsInLayout() // отличается от removeAllView
            }

            // получить экран ориентации
            val orientation = requireActivity().resources.configuration.orientation
            // раздуть соответствующий макет в зависимости от ориентации экрана
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                view = layoutInflater.inflate(R.layout.fragment_tab_view_pager2, viewGroupTabViewPager2, false)
                START_POS_X = -800F
                END_POS_X = 800F

                myRecyclerLayoutManagerAdapter(view)
            } else {
                view = layoutInflater.inflate(R.layout.fragment_tab_view_pager2_horiz, viewGroupTabViewPager2, false)
                START_POS_X = -1200F
                END_POS_X = 1200F

                myRecyclerLayoutManagerAdapter(view)
            }

            return view // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
        }

        fun myRecyclerLayoutManagerAdapter(view: View) {

            viewModelTabViewPager2 = ViewModelProvider(this).get(ViewModelTabViewPager2::class.java)

            viewPager2 = view.findViewById(R.id.viewPager2)
            tabLayout = view.findViewById(R.id.tabLayout)

            titleTab = ArrayList() // добавить заголовки для табов
            titleTab.add("Players")
            titleTab.add("Teams")
            titleTab.add("Games")

            fragmentList = ArrayList<Fragment>() // записать в массив все фрагменты для табов
            fragmentList.add(FragmentPlayers())
            fragmentList.add(FragmentTeams())
            fragmentList.add(FragmentGames())

            val adapterViewPager2 = AdapterViewPager2(requireActivity(), fragmentList)

            adapterViewPager2.setData(fragmentList)

            viewPager2.adapter = adapterViewPager2

            viewPager2.offscreenPageLimit = 3
            tabLayout.selectTab(tabLayout.getTabAt(viewModelTabViewPager2.lastTab))
            viewPager2.currentItem = viewModelTabViewPager2.lastTab
            Log.e("333","viewModelTabViewPager2.lastTab=" + viewModelTabViewPager2.lastTab)

            // TabLayoutMediator для синхронизации компонента TabLayout с ViewPager2
            // установка текста заголовков вкладок, стиля вкладок
            TabLayoutMediator(tabLayout, viewPager2, object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    try {
                        tab.setText(titleTab[position])
                    }
                    catch (e:Exception) {
                        Log.e("444", " try catch TabLayoutMediator$e")
                    }
                }
            }).attach()
            // слушатель для viewPager2
            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                        viewModelTabViewPager2.lastTab = position
                    Log.e("333", " onPageSelected position$position")
                     super.onPageSelected(position)
                }
                override fun onPageScrollStateChanged(state: Int) {
                    //tabLayout.selectTab(tabLayout.getTabAt(viewModelTabViewPager2.lastTab))
                    super.onPageScrollStateChanged(state)
                }
            })

            val mAnimation: Animation = TranslateAnimation(
                START_POS_X!!, END_POS_X!!,
                START_POS_Y, END_POS_Y
            )
            mAnimation.duration = TICKER_DURATION
            mAnimation.repeatMode = Animation.RESTART
            mAnimation.repeatCount = Animation.INFINITE

            tvTitle = view.findViewById(R.id.tvTitle)
            tvTitle.animation = mAnimation

        }


}