package com.dev_marinov.nbadata

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


class FragmentTabViewPager2 : Fragment() {


    val START_POS_X: Float = -800F
    val END_POS_X: Float = 800F
    val START_POS_Y: Float = 0F
    val END_POS_Y: Float = 0F
    val TICKER_DURATION: Long = 5000

    lateinit var tvTitle: TextView

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var titleTab: ArrayList<String> // массив названий табов заголовков
    lateinit var fragmentList: ArrayList<Fragment> // массив табов фрагментов
    var adapterViewPager2: AdapterViewPager2? = null

    var viewGroupTabViewPager2: ViewGroup? = null
    var myLayoutInflater: LayoutInflater? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewGroupTabViewPager2 = container
        myLayoutInflater = inflater

        return initInterface()
    }

    // https://stackoverflow.com/questions/54266160/changing-a-recyclerviews-layout-upon-orientation-change-only-works-on-the-first
    fun initInterface(): View? { // удалить android:configChanges из манифеста для применения данной стратегии
        val view: View
        // если уже есть надутый макет, удалить его.
        if (viewGroupTabViewPager2 != null) {
            viewGroupTabViewPager2!!.removeAllViewsInLayout() // отличается от removeAllView
        }
        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_tab_view_pager2, viewGroupTabViewPager2, false)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_tab_view_pager2, viewGroupTabViewPager2, false)
        }

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

        adapterViewPager2 = AdapterViewPager2(requireActivity(), fragmentList)

        adapterViewPager2?.setData(fragmentList)

        viewPager2.adapter = adapterViewPager2

        viewPager2.offscreenPageLimit = 4

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
                (activity as MainActivity).lastTab = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
        //восстановить состояние последнего выбранного таба
//        tabLayout.selectTab(tabLayout.getTabAt((activity as MainActivity).lastTab!!))


        val mAnimation: Animation = TranslateAnimation(
            START_POS_X, END_POS_X,
            START_POS_Y, END_POS_Y
        )
        mAnimation.duration = TICKER_DURATION
        mAnimation.repeatMode = Animation.RESTART
        mAnimation.repeatCount = Animation.INFINITE

        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setAnimation(mAnimation);


        return view // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.e("333", "-зашел FragmentHome onConfigurationChanged-")
        // ДО СОЗДАНИЯ НОВОГО МАКЕТА ПИШЕМ ПЕРЕМЕННЫЕ В КОТОРЫЕ СОХРАНЯЕМ ЛЮБЫЕ ДАННЫЕ ИЗ ТЕКУЩИХ VIEW
        // создать новый макет------------------------------
        val view: View = initInterface()!!
        // ПОСЛЕ СОЗДАНИЯ НОВОГО МАКЕТА ПЕРЕДАЕМ СОХРАНЕННЫЕ ДАННЫЕ В СТАРЫЕ(ТЕ КОТОРЫЕ ТЕКУЩИЕ) VIEW
        // отображать новую раскладку на экране
        viewGroupTabViewPager2?.addView(view)
        super.onConfigurationChanged(newConfig)
    }

}