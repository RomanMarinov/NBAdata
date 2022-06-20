package com.dev_marinov.nbadata.presentation.teams

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListTeams
import com.dev_marinov.nbadata.databinding.ItemTeamsBinding

class TeamsAdapter() : RecyclerView.Adapter<TeamsAdapter.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListTeams> = HashMap()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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
    fun refreshTeams(hashMap: HashMap<Int, ObjectListTeams>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemTeamsBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(objectListTeams: ObjectListTeams) {

            binding.teams = objectListTeams

            binding.tvName.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvTeamsCity.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvTeamsConference.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvTeamsDivision.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация

            binding.executePendingBindings()
        }

    }
}