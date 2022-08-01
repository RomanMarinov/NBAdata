package com.dev_marinov.nbadata.presentation.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.domain.players.Players
import com.dev_marinov.nbadata.databinding.ItemPlayersBinding

class PlayersAdapter() : RecyclerView.Adapter<PlayersAdapter.ViewHolder>(){

    private var players: List<Players> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemPlayersBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(players[position]!!)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshPlayers(players: List<Players>) {
        this.players = players
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemPlayersBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(players: Players) {
            binding.players = players

            // установка "n/f", если objectListPlayers.position пустой
            if (players.position == "") {
                binding.tvPosition.text = "n/f"
            } else {
                binding.tvPosition.text = players.position
            }

            binding.tvFirstNamePlayers.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvLastNamePlayers.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvNameTeam.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvCity.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvConference.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvDivision.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvPosition.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация

            binding.executePendingBindings()
        }
    }
}