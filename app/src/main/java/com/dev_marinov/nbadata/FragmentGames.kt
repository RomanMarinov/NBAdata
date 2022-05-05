package com.dev_marinov.nbadata

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.Exception

class FragmentGames : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapterListGames: AdapterListGames? = null
    var myViewGroup: ViewGroup? = null
    var myLayoutInflater: LayoutInflater? = null
    var gridLayoutManager: GridLayoutManager? = null

    var lastVisibleItemPositions: IntArray? = null
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        myViewGroup = container
        myLayoutInflater = inflater
        // отображать желаемую разметку и возвращать view в initInterface .
        // onCreateView() возвращает объект View, который является корневым элементом разметки фрагмента.
        return initInterface()
    }

    // https://stackoverflow.com/questions/54266160/changing-a-recyclerviews-layout-upon-orientation-change-only-works-on-the-first
    fun initInterface(): View? { // удалить android:configChanges из манифеста для применения данной стратегии
        val view: View
        // если уже есть надутый макет, удалить его.
        if (myViewGroup != null) {
            myViewGroup!!.removeAllViewsInLayout() // отличается от removeAllView
        }
        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_games, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 1, (activity as MainActivity?)?.lastVisibleItemGames)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_games, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 2, (activity as MainActivity?)?.lastVisibleItemGames)
        }

        if ((activity as MainActivity?)?.hashMapGames?.size == 0) {
            Log.e("333", "arrayList.size()=" + (activity as MainActivity?)?.hashMapGames?.size)

            val requestDataGames = RequestDataGames()
            requestDataGames.getData(requireActivity())

            (activity as MainActivity).setMyInterFaceGames(object :MainActivity.MyInterFaceGames {
                override fun methodMyInterFaceGames() {
                    (activity as MainActivity).runOnUiThread {
                        adapterListGames?.notifyDataSetChanged()
                    }
                }
            })

        } else {
            Log.e("333", "FragmentHome arrayList.size()  НЕ ПУСТОЙ=")
        }

        return view // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.e("333", "-зашел FragmentHome onConfigurationChanged-")
        // ДО СОЗДАНИЯ НОВОГО МАКЕТА ПИШЕМ ПЕРЕМЕННЫЕ В КОТОРЫЕ СОХРАНЯЕМ ЛЮБЫЕ ДАННЫЕ ИЗ ТЕКУЩИХ VIEW
        // создать новый макет------------------------------
//        val view: View = initInterface()!!
//        // ПОСЛЕ СОЗДАНИЯ НОВОГО МАКЕТА ПЕРЕДАЕМ СОХРАНЕННЫЕ ДАННЫЕ В СТАРЫЕ(ТЕ КОТОРЫЕ ТЕКУЩИЕ) VIEW
//        // отображать новую раскладку на экране
//        myViewGroup?.addView(view)
        super.onConfigurationChanged(newConfig)
    }

    fun myRecyclerLayoutManagerAdapter(view: View, column: Int, lastVisibleItem: Int?) {

        recyclerView = view.findViewById(R.id.recyclerView)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        adapterListGames = AdapterListGames(this.requireActivity(), (activity as MainActivity).hashMapGames, recyclerView)

        recyclerView.adapter = adapterListGames

        // слушатель recyclerView для сохранения последнего видимого элемента экрана
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                lastVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)

                (context as MainActivity).lastVisibleItemGames = getMaxPosition(lastVisibleItemPositions!!)
            }

            private fun getMaxPosition(positions: IntArray): Int {
                return positions[0]
            }
        })

        val runnable = Runnable { // установка последнего элемента в главном потоке
            try {
                requireActivity().runOnUiThread {
                    staggeredGridLayoutManager.scrollToPositionWithOffset(lastVisibleItem!!, 0)
                }
            } catch (e: Exception) {
                Log.e("333", "-try catch FragmentHome 1-$e")
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 500)


    }


}