package com.dev_marinov.nbadata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class AdapterListGames (val requireActivity: FragmentActivity, var hashMap: HashMap<Int, ObjectListGames>, recyclerView: RecyclerView)
    : RecyclerView.Adapter<AdapterListGames.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListGames.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_games, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterListGames.ViewHolder, position: Int) {
        var objectListGames = hashMap[position]

        if(objectListGames != null) {

            holder.date.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.teamHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.cityHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.conferenceHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.divisionHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.scoreHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.periodHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.seasonHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.statusHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            holder.teamVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.conferenceVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.cityVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.divisionVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.scoreVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация


            holder.date.text = objectListGames.date.toString().substring(0, 10)
            holder.teamHome.text = objectListGames.teamHome.toString()
            holder.cityHome.text = objectListGames.cityHome.toString()
            holder.conferenceHome.text = objectListGames.conferenceHome.toString()
            holder.divisionHome.text = objectListGames.divisionHome.toString()
            holder.scoreHome.text = objectListGames.scoreHome.toString()
            holder.periodHome.text = objectListGames.periodHome.toString()
            holder.seasonHome.text = objectListGames.seasonHome.toString()
            holder.statusHome.text = objectListGames.statusHome.toString()

            holder.teamVisitor.text = objectListGames.teamVisitor.toString()
            holder.conferenceVisitor.text = objectListGames.conferenceVisitor.toString()
            holder.cityVisitor.text = objectListGames.cityVisitor.toString()
            holder.divisionVisitor.text = objectListGames.divisionVisitor.toString()
            holder.scoreVisitor.text = objectListGames.scoreVisitor.toString()

//            if (!objectList.position.toString().equals("")) {
//                holder.position.setText(objectList.position.toString())
//            } else {
//                holder.position.setText("n/f")
//            }
        }
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById<TextView>(R.id.tvDate)
        val teamHome: TextView = itemView.findViewById<TextView>(R.id.tvNameTeam)
        val cityHome: TextView = itemView.findViewById<TextView>(R.id.tvCity)
        val conferenceHome: TextView = itemView.findViewById<TextView>(R.id.tvConference)
        val divisionHome: TextView = itemView.findViewById<TextView>(R.id.tvDivisionHome)
        val scoreHome: TextView = itemView.findViewById<TextView>(R.id.tvScore)
        val periodHome: TextView = itemView.findViewById<TextView>(R.id.tvPeriod)
        val seasonHome: TextView = itemView.findViewById<TextView>(R.id.tvSeason)
        val statusHome: TextView = itemView.findViewById<TextView>(R.id.tvStatus)

        val teamVisitor: TextView = itemView.findViewById<TextView>(R.id.tvNameVisitor)
        val conferenceVisitor: TextView = itemView.findViewById<TextView>(R.id.tvConferenceVisitor)
        val cityVisitor: TextView = itemView.findViewById<TextView>(R.id.tvCityVisitor)
        val divisionVisitor: TextView = itemView.findViewById<TextView>(R.id.tvDivisionVisitor)
        val scoreVisitor: TextView = itemView.findViewById<TextView>(R.id.tvScoreVisitor)

    }
}