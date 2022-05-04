package com.dev_marinov.nbadata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class AdapterListTeams(var requireActivity: FragmentActivity, var hashMapTeams: HashMap<Int, ObjectListTeams>, recyclerView: RecyclerView)
    : RecyclerView.Adapter<AdapterListTeams.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListTeams.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_teams, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterListTeams.ViewHolder, position: Int) {
        val objectListTeams = hashMapTeams[position]

        if(objectListTeams != null) {
            holder.fullName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.city.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.conference.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.division.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            holder.fullName.setText(objectListTeams.fullName.toString())
            holder.city.setText(objectListTeams.city.toString())
            holder.conference.setText(objectListTeams.conference.toString())
            holder.division.setText(objectListTeams.division.toString())
        }
    }

    override fun getItemCount(): Int {
        return hashMapTeams.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var fullName: TextView = itemView.findViewById<TextView>(R.id.tvName)
        var city: TextView = itemView.findViewById<TextView>(R.id.tvCity)
        var conference: TextView = itemView.findViewById<TextView>(R.id.tvConference)
        var division: TextView = itemView.findViewById<TextView>(R.id.tvDivision)
    }
}