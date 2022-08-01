package com.dev_marinov.nbadata.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.domain.games.Games
import com.dev_marinov.nbadata.databinding.ItemGamesBinding

class GamesAdapter() : ListAdapter<Games, GamesAdapter.ViewHolder>(GamesDiffUtilCallback()) {

    private var games: List<Games> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemGamesBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun submitList(list: List<Games>?) {
        super.submitList(list)
        list?.let { this.games = it.toList() }
    }

    inner class ViewHolder(
        private val binding: ItemGamesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(games: Games) {
            binding.games = games
            setAnimation()
            binding.executePendingBindings()
        }

        private fun setAnimation() {
            // установка анимации
            binding.tvGamesDate.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesNameTeam.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesCity.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesConference.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesDivisionHome.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesScore.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesPeriod.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesSeason.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesStatus.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация

            binding.tvGamesNameVisitor.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesConferenceVisitor.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesCityVisitor.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesDivisionVisitor.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvGamesScoreVisitor.animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
        }
    }

    class GamesDiffUtilCallback : DiffUtil.ItemCallback<Games>() {
        override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
            return oldItem == newItem
        }

    }

}

