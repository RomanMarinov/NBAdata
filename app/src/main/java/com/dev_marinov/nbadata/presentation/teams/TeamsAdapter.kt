package com.dev_marinov.nbadata.presentation.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.domain.Teams
import com.dev_marinov.nbadata.databinding.ItemTeamsBinding

class TeamsAdapter() : RecyclerView.Adapter<TeamsAdapter.ViewHolder>(){

    private var hashMap: HashMap<Int, Teams> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTeamsBinding.inflate(inflater,parent,false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshTeams(hashMap: HashMap<Int, Teams>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemTeamsBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(teams: Teams) {

            binding.teams = teams

            binding.tvName.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsCity.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsConference.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация
            binding.tvTeamsDivision.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1) // анимация

            binding.executePendingBindings()
        }

    }
}