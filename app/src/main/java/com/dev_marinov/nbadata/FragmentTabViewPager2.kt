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

class FragmentTabViewPager2 : Fragment() {

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var titleTab: ArrayList<String> // массив названий табов заголовков
    lateinit var fragmentList: ArrayList<String> // массив табов фрагментов
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
            view = layoutInflater.inflate(R.layout.fragment_list, viewGroupTabViewPager2, false)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_list, viewGroupTabViewPager2, false)
        }

        viewGroupTabViewPager2 = view.findViewById(R.id.viewPager2)
        tabLayout = view.findViewById(R.id.tabLayout)

        titleTab = ArrayList()
        titleTab.add("Players")
        titleTab.add("Games")
        titleTab.add("Teams")
        titleTab.add("Stats")

        fragmentList = ArrayList()


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