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

class FragmentTeams : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewModelTeams: ViewModelFragTeams
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View

        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation
        // раздуть соответствующий макет в зависимости от ориентации экрана
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = layoutInflater.inflate(R.layout.fragment_teams, container, false)

            myRecyclerLayoutManagerAdapter(view, 1)
        } else {
            view = layoutInflater.inflate(R.layout.fragment_teams, container, false)

            myRecyclerLayoutManagerAdapter(view, 2)
        }

        return view
    }



    fun myRecyclerLayoutManagerAdapter(view: View, column: Int) {

        viewModelTeams = ViewModelProvider(this).get(ViewModelFragTeams::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        val adapterListTeams = AdapterListTeams(this.requireActivity())

        recyclerView.adapter = adapterListTeams

        viewModelTeams.getHashMapTeams().observe(requireActivity(), Observer {
            it.let { adapterListTeams.refreshUsers(it) }
        })

        setMyInterFaceTeams(object : FragmentTeams.MyInterFaceTeams {
            override fun methodMyInterFaceTeams() {
                (activity as MainActivity).runOnUiThread {
                    adapterListTeams.notifyDataSetChanged()
                }
            }
        })
    }

    companion object{
        lateinit var myInterFaceTeams : MyInterFaceTeams
    }

    // интерфейс для работы с FragmentTeams
    interface MyInterFaceTeams{
        fun methodMyInterFaceTeams()
    }
    fun setMyInterFaceTeams(myInterFaceTeams: MyInterFaceTeams) {
        Companion.myInterFaceTeams = myInterFaceTeams
    }

}