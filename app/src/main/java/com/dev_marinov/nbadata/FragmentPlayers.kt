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

open class FragmentPlayers : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapterListPlayers: AdapterListPlayers? = null
    var myViewGroup: ViewGroup? = null
    var myLayoutInflater: LayoutInflater? = null

    var lastVisibleItemPositions: IntArray? = null
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("333", "зашел FragmentPlayers")
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
            view = layoutInflater.inflate(R.layout.fragment_players, myViewGroup, false)
            Log.e("333", "ПОРТРЕТ FragmentPlayers")
            myRecyclerLayoutManagerAdapter(view, 1, (activity as MainActivity?)?.lastVisibleItemPlayers)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_players, myViewGroup, false)
            Log.e("333", "АЛЬБОМ FragmentPlayers")
            myRecyclerLayoutManagerAdapter(view, 2, (activity as MainActivity?)?.lastVisibleItemPlayers)
        }

        if ((activity as MainActivity?)?.hashMapPlayers?.size == 0) {
            Log.e("333", "arrayList.size()=" + (activity as MainActivity?)?.hashMapPlayers?.size)

            val requestDataPlayers = RequestDataPlayers()// получаем доступ к классу
            requestDataPlayers.getData(requireActivity()) // передаем в метод контекст

                // как только getData сработал интерфейс, мы  FragmentPlayers обновляем адаптер
            // (это значит что данные из сети пришли и можно обновить адаптер)
            (activity as MainActivity).setMyInterFacePlayers(object : MainActivity.MyInterFacePlayers {
                override fun methodMyInterFacePlayers() {
                    (activity as MainActivity).runOnUiThread {
                        adapterListPlayers?.notifyDataSetChanged()
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
         Log.e("333", "myRecyclerLayoutManagerAdapter FragmentPlayers")
         recyclerView = view.findViewById(R.id.recyclerView)
         // setHasFixedSize(true), то подразумеваете, что размеры самого RecyclerView будет оставаться неизменными.
         // Если вы используете setHasFixedSize(false), то при каждом добавлении/удалении элементов RecyclerView
         // будет перепроверять свои размеры
         recyclerView.setHasFixedSize(false)

         staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
         recyclerView.layoutManager = staggeredGridLayoutManager

         adapterListPlayers = AdapterListPlayers(this.requireActivity(), (activity as MainActivity).hashMapPlayers, recyclerView)

         recyclerView.adapter = adapterListPlayers

         // слушатель recyclerView для сохранения последнего видимого элемента экрана
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                lastVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)

                (context as MainActivity).lastVisibleItemPlayers = getMaxPosition(lastVisibleItemPositions!!)
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