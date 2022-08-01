package com.dev_marinov.nbadata.presentation.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.domain.teams.Teams
import com.dev_marinov.nbadata.databinding.ItemTeamsBinding
import com.dev_marinov.nbadata.domain.players.Players

class TeamsAdapter() : ListAdapter<Teams, TeamsAdapter.ViewHolder>(TeamsDiffUtilCallBack()) {

    private var teams: List<Teams> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTeamsBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun submitList(list: List<Teams>?) {
        super.submitList(list)
        list?.let { this.teams = it.toList() }
    }

    inner class ViewHolder(private var binding: ItemTeamsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teams: Teams) {
            binding.teams = teams
            setAnimation()
            binding.executePendingBindings()
        }

        private fun setAnimation() {
            binding.tvName.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsCity.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsConference.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsDivision.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
        }

    }

    class TeamsDiffUtilCallBack : DiffUtil.ItemCallback<Teams>() {
        override fun areItemsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem == newItem
        }

    }
}


