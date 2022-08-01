package com.dev_marinov.nbadata.presentation.players

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.FragmentPlayersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class PlayersFragment : Fragment() {

    lateinit var binding: FragmentPlayersBinding
    lateinit var playersViewModel: PlayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initInterFace(inflater, container)
    }

    private fun initInterFace(inflater: LayoutInflater, container: ViewGroup?): View{

        container?.let { container.removeAllViewsInLayout() }

        val orientation = requireActivity().resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false)
            initViewModel()
            setUpPlayersRecyclerView(1)
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false)
            initViewModel()
            setUpPlayersRecyclerView(2)
        }

        return binding.root
    }

    private fun initViewModel() {
        playersViewModel = ViewModelProvider(this)[PlayersViewModel::class.java]
    }

     private fun setUpPlayersRecyclerView(column: Int) {

         val staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
         val adapterListPlayers = PlayersAdapter()

         binding.recyclerView.apply {
             setHasFixedSize(false)
             layoutManager = staggeredGridLayoutManager
             adapter = adapterListPlayers

         }

         playersViewModel.players.observe(viewLifecycleOwner) { players ->
             adapterListPlayers.submitList(players)
         }

     }

}