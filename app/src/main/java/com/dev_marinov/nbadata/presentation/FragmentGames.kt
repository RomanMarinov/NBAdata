package com.dev_marinov.nbadata.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_marinov.nbadata.R

class FragmentGames : Fragment() {

    lateinit var viewModelGames: ViewModelFragGames
    lateinit var recyclerView: RecyclerView
    var adapterListGames: AdapterListGames? = null
    var myViewGroup: ViewGroup? = null
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myViewGroup = container

        return initInterface()
    }
    fun initInterface(): View? {
        val view: View
        if (myViewGroup != null) {
            myViewGroup!!.removeAllViewsInLayout() // отличается от removeAllView
        }
        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_games, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 1)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_games, myViewGroup, false)

            myRecyclerLayoutManagerAdapter(view, 2)
        }


        return view // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
    }

    fun myRecyclerLayoutManagerAdapter(view: View, column: Int) {

        viewModelGames = ViewModelProvider(this).get(ViewModelFragGames::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        adapterListGames = AdapterListGames(this.requireActivity())

        recyclerView.adapter = adapterListGames

        // подписываем адаптер на изменение списка
        viewModelGames.getHashMapGames().observe(requireActivity(), Observer {
            it.let { adapterListGames!!.refreshUsers(it) } // it - обновленный список
        })

        setMyInterFaceGames(object : FragmentGames.MyInterFaceGames {
            override fun methodMyInterFaceGames() {
                try {
                (activity as MainActivity).runOnUiThread {
                    adapterListGames!!.notifyDataSetChanged()
                }

                }
                catch (e:Exception){

                }
            }
        })

    }

    companion object{
        lateinit var myInterFaceGames: MyInterFaceGames
    }

    // интерфейс для работы с FragmentGames
    interface MyInterFaceGames{
        fun methodMyInterFaceGames()
    }
    fun setMyInterFaceGames(myInterFaceGames: MyInterFaceGames) {
        Companion.myInterFaceGames = myInterFaceGames
    }

}