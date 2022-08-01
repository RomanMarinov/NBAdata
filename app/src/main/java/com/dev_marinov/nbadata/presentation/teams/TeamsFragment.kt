package com.dev_marinov.nbadata.presentation.teams

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private lateinit var binding: FragmentTeamsBinding
    lateinit var viewModelTeamsViewModel: TeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        return initInterFace(inflater, container)
    }

    private fun initInterFace(inflater: LayoutInflater, container: ViewGroup?): View {

        container?.let { container.removeAllViewsInLayout() }

        val orientation = requireActivity().resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)
            initViewModel()
            setUpTeamsRecyclerView(1)
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)
            initViewModel()
            setUpTeamsRecyclerView( 2)
        }

        return binding.root
    }

    private fun initViewModel() {
        viewModelTeamsViewModel = ViewModelProvider(this)[TeamsViewModel::class.java]
    }

    private fun setUpTeamsRecyclerView(column: Int) {

        val teamsAdapter = TeamsAdapter()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = teamsAdapter
        }

        viewModelTeamsViewModel.teams.observe(viewLifecycleOwner) { team ->
            teamsAdapter.refreshTeams(team)
        }
    }

}