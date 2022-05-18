package com.dev_marinov.nbadata.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_marinov.nbadata.R

open class FragmentPlayers : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewModelPlayers: ViewModelFragPlayers
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("333", "зашел FragmentPlayers")
        val view: View

        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_players, container, false)
            Log.e("333", "ПОРТРЕТ FragmentPlayers")
            myRecyclerLayoutManagerAdapter(view, 1)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_players, container, false)
            Log.e("333", "АЛЬБОМ FragmentPlayers")
            myRecyclerLayoutManagerAdapter(view, 2)
        }

        return view
    }



     fun myRecyclerLayoutManagerAdapter(view: View, column: Int) {
         Log.e("333", "myRecyclerLayoutManagerAdapter FragmentPlayers")

         viewModelPlayers = ViewModelProvider(this).get(ViewModelFragPlayers::class.java)

         recyclerView = view.findViewById(R.id.recyclerView)
         // setHasFixedSize(true), то подразумеваете, что размеры самого RecyclerView будет оставаться неизменными.
         // Если вы используете setHasFixedSize(false), то при каждом добавлении/удалении элементов RecyclerView
         // будет перепроверять свои размеры
         recyclerView.setHasFixedSize(false)

         staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
         recyclerView.layoutManager = staggeredGridLayoutManager

         val adapterListPlayers = AdapterListPlayers(this.requireActivity())

         recyclerView.adapter = adapterListPlayers

         // подписываем адаптер на изменение списка
         viewModelPlayers.getHashMapPlayers().observe(requireActivity(), Observer {
             it.let { adapterListPlayers.refreshUsers(it) } // it - обновленный список
         })

         setMyInterFacePlayers(object : FragmentPlayers.MyInterFacePlayers {
             override fun methodMyInterFacePlayers() {
                 (activity as MainActivity).runOnUiThread {
                    adapterListPlayers.notifyDataSetChanged()
                 }
             }
         })
     }

    companion object{
        lateinit var myInterFacePlayers: MyInterFacePlayers
    }

    // интерфейс для работы с FragmentPlayers
    interface MyInterFacePlayers{
        fun methodMyInterFacePlayers()
    }
    fun setMyInterFacePlayers(myInterFacePlayers: MyInterFacePlayers) {
        Companion.myInterFacePlayers = myInterFacePlayers
    }

}