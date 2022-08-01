package com.dev_marinov.nbadata.presentation.games

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.FragmentGamesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesFragment : Fragment() {

    private lateinit var binding: FragmentGamesBinding
    private lateinit var gamesViewModel: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return initInterFace(inflater, container)
    }

    private fun initInterFace(inflater: LayoutInflater, container: ViewGroup?): View {

        container?.let { container.removeAllViewsInLayout() }

        // получить экран ориентации
        val orientation = requireActivity().resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false)
            initViewModel()
            setUpGamesRecyclerView(1)
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false)
            initViewModel()
            setUpGamesRecyclerView(2)
        }

        return binding.root // в onCreateView() возвращаем объект View, который является корневым элементом разметки фрагмента.
    }

    private fun initViewModel(){
        gamesViewModel = ViewModelProvider(this)[GamesViewModel::class.java]
    }

    private fun setUpGamesRecyclerView(column: Int) {
        val gamesAdapter = GamesAdapter()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)

        binding.recyclerView.apply {
            layoutManager = staggeredGridLayoutManager
            setHasFixedSize(false) // т.к. размеры view у списка разные, то rv будет перепроверять их размер
            adapter = gamesAdapter
        }

        // подписываем адаптер на изменение списка
        gamesViewModel.games.observe(viewLifecycleOwner) {
            gamesAdapter.submitList(it)
        }

    }



}