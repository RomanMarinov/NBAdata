package com.dev_marinov.nbadata.presentation.players

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListPlayers
import com.dev_marinov.nbadata.databinding.ItemPlayersBinding

class PlayersAdapter() : RecyclerView.Adapter<PlayersAdapter.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListPlayers> = HashMap()
    private lateinit var context: Context // только для анимации views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemPlayersBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshPlayers(hashMap: HashMap<Int, ObjectListPlayers>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemPlayersBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(objectListPlayers: ObjectListPlayers) {
            binding.players = objectListPlayers

            // установка "n/f", если objectListPlayers.position пустой
            if (objectListPlayers.position == "") {
                binding.tvPosition.text = "n/f"
            } else {
                binding.tvPosition.text = objectListPlayers.position
            }

            binding.tvFirstNamePlayers.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvLastNamePlayers.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvNameTeam.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvCity.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvConference.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvDivision.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvPosition.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация

            binding.executePendingBindings()
        }
    }
}