package com.dev_marinov.nbadata.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListPlayers

class AdapterListPlayers(val requireActivity: FragmentActivity) : RecyclerView.Adapter<AdapterListPlayers.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListPlayers> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_players, parent, false)
        return ViewHolder(view, requireActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)

//        val objectList = hashMap[position]
//
//        if(objectList != null) {
//
//
//        }
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(hashMap: HashMap<Int, ObjectListPlayers>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, var requireActivity: FragmentActivity) :RecyclerView.ViewHolder(itemView) {

        fun bind(item: ObjectListPlayers) = with(itemView) {

            val firstName: TextView = itemView.findViewById<TextView>(R.id.tvFirstNamePlayers)
            val lastName: TextView = itemView.findViewById<TextView>(R.id.tvLastNamePlayers)
            val team: TextView = itemView.findViewById<TextView>(R.id.tvNameTeam)
            val city: TextView = itemView.findViewById<TextView>(R.id.tvCity)
            val conference: TextView = itemView.findViewById<TextView>(R.id.tvConference)
            val division: TextView = itemView.findViewById<TextView>(R.id.tvDivision)
            val position: TextView = itemView.findViewById<TextView>(R.id.tvPosition)

            firstName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            lastName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            team.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            city.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            conference.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            division.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            position.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            // установка текста в views
            firstName.text = item.firstName
            lastName.text = item.lastName
            team.text = item.team
            city.text = item.city
            conference.text = item.conference
            division.text = item.division

            // в position не всегда есть данные, поэтому условие
            if (!item.position.toString().equals("")) {
                position.text = item.position
            } else {
                position.text = "n/f"
            }

        }
    }
}