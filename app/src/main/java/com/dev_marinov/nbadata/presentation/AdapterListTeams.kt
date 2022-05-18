package com.dev_marinov.nbadata.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListTeams

class AdapterListTeams(var requireActivity: FragmentActivity) : RecyclerView.Adapter<AdapterListTeams.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListTeams> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_teams, parent, false)
        return ViewHolder(view, requireActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }


    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(hashMap: HashMap<Int, ObjectListTeams>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View, var requireActivity: FragmentActivity) :RecyclerView.ViewHolder(itemView) {

        fun bind(item: ObjectListTeams) = with(itemView) {

            val fullName: TextView = itemView.findViewById<TextView>(R.id.tvName)
            val city: TextView = itemView.findViewById<TextView>(R.id.tvCity)
            val conference: TextView = itemView.findViewById<TextView>(R.id.tvConference)
            val division: TextView = itemView.findViewById<TextView>(R.id.tvDivision)

            fullName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            city.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            conference.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            division.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            // установка текста в views
            fullName.text = item.firstName
            city.text = item.city
            conference.text = item.conference
            division.text = item.division
        }

    }
}