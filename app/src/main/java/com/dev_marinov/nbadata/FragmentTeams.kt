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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.Exception


class FragmentTeams : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapterListTeams: AdapterListTeams? = null
    var viewGroupTeams: ViewGroup? = null
    var myLayoutInflater: LayoutInflater? = null

    var lastVisibleItemPositions: IntArray? = null
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewGroupTeams = container
        myLayoutInflater = inflater
        // отображать желаемую разметку и возвращать view в initInterface .
        // onCreateView() возвращает объект View, который является корневым элементом разметки фрагмента.
        return initInterface()
    }

    // https://stackoverflow.com/questions/54266160/changing-a-recyclerviews-layout-upon-orientation-change-only-works-on-the-first
    fun initInterface(): View? { // удалить android:configChanges из манифеста для применения данной стратегии
        val view: View
        // если уже есть надутый макет, удалить его.
        if (viewGroupTeams != null) {
            viewGroupTeams!!.removeAllViewsInLayout() // отличается от removeAllView
        }
        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_teams, viewGroupTeams, false)

            myRecyclerLayoutManagerAdapter(view, 1, (activity as MainActivity?)?.lastVisibleItemTeams)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_teams, viewGroupTeams, false)

            myRecyclerLayoutManagerAdapter(view, 2, (activity as MainActivity?)?.lastVisibleItemTeams)
        }

        if ((activity as MainActivity?)?.hashMapTeams?.size == 0) {
            Log.e("333", "arrayList.size()=" + (activity as MainActivity?)?.hashMapTeams?.size)

            val requestDataTeams = RequestDataTeams()// получаем доступ к классу
            requestDataTeams.getData(requireActivity()) // передаем в метод контекст
            // как только getData сработал интерфейс, мы  FragmentTeams обновляем адаптер
            // (это значит что данные из сети пришли и можно обновить адаптер)
            (activity as MainActivity).setMyInterFaceTeams(object : MainActivity.MyInterFaceTeams{
                override fun methodMyInterFaceTeams() {
                    (activity as MainActivity).runOnUiThread {
                        adapterListTeams?.notifyDataSetChanged()
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
//        viewGroupTeams?.addView(view)
        super.onConfigurationChanged(newConfig)
    }

    fun myRecyclerLayoutManagerAdapter(view: View, column: Int, lastVisibleItem: Int?) {

        recyclerView = view.findViewById(R.id.recyclerView)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        adapterListTeams = AdapterListTeams(this.requireActivity(), (activity as MainActivity).hashMapTeams, recyclerView)

        recyclerView.adapter = adapterListTeams

        // слушатель recyclerView для сохранения последнего видимого элемента экрана
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                lastVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)

                (context as MainActivity).lastVisibleItemTeams = getMaxPosition(lastVisibleItemPositions!!)
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