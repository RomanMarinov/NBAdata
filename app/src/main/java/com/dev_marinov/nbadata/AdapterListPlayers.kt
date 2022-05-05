package com.dev_marinov.nbadata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView


class AdapterListPlayers(val requireActivity: FragmentActivity, var hashMap: HashMap<Int, ObjectListPlayers>, recyclerView: RecyclerView)
    : RecyclerView.Adapter<AdapterListPlayers.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListPlayers.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_players, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterListPlayers.ViewHolder, position: Int) {
        val objectList = hashMap[position]

        if(objectList != null) {

            holder.firstName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.lastName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.position.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.team.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.city.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.conference.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.division.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            // установка текста в views
            holder.firstName.text = objectList.firstName.toString()
            holder.lastName.text = objectList.lastName.toString()
            holder.team.text = objectList.team.toString()
            holder.city.text = objectList.city.toString()
            holder.conference.text = objectList.conference.toString()
            holder.division.text = objectList.division.toString()

            // в position не всегда есть данные, поэтому условие
            if (!objectList.position.toString().equals("")) {
                holder.position.text = objectList.position.toString()
            } else {
                holder.position.text = "n/f"
            }
        }
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var firstName: TextView = itemView.findViewById<TextView>(R.id.tvFirstNamePlayers)
        var lastName: TextView = itemView.findViewById<TextView>(R.id.tvLastNamePlayers)
        var position: TextView = itemView.findViewById<TextView>(R.id.tvPosition)
        var team: TextView = itemView.findViewById<TextView>(R.id.tvNameTeam)
        var city: TextView = itemView.findViewById<TextView>(R.id.tvCity)
        var conference: TextView = itemView.findViewById<TextView>(R.id.tvConference)
        var division: TextView = itemView.findViewById<TextView>(R.id.tvDivision)
    }
}