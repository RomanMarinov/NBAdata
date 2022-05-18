package com.dev_marinov.nbadata.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListGames

class AdapterListGames (val requireActivity: FragmentActivity) : RecyclerView.Adapter<AdapterListGames.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListGames> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_games, parent, false)
        return ViewHolder(view, requireActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)
    }

    override fun getItemCount(): Int {
//        Log.e("333","-getItemCount games hashMap.size=" + hashMap.size)
        return hashMap.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(hashMap: HashMap<Int, ObjectListGames>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, var requireActivity: FragmentActivity) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ObjectListGames) = with(itemView) {
            val date: TextView = itemView.findViewById<TextView>(R.id.tvGamesDate)

            val teamHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesNameTeam)
            val cityHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesCity)
            val conferenceHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesConference)
            val divisionHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesDivisionHome)
            val scoreHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesScore)
            val periodHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesPeriod)
            val seasonHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesSeason)
            val statusHome: TextView = itemView.findViewById<TextView>(R.id.tvGamesStatus)

            val teamVisitor: TextView = itemView.findViewById<TextView>(R.id.tvGamesNameVisitor)
            val conferenceVisitor: TextView = itemView.findViewById<TextView>(R.id.tvGamesConferenceVisitor)
            val cityVisitor: TextView = itemView.findViewById<TextView>(R.id.tvGamesCityVisitor)
            val divisionVisitor: TextView = itemView.findViewById<TextView>(R.id.tvGamesDivisionVisitor)
            val scoreVisitor: TextView = itemView.findViewById<TextView>(R.id.tvGamesScoreVisitor)

            // установка анимации
            date.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            teamHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            cityHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            conferenceHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            divisionHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            scoreHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            periodHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            seasonHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            statusHome.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            teamVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            conferenceVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            cityVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            divisionVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            scoreVisitor.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            // установка текста в views
            date.text = item.date.toString().substring(0, 10)
            teamHome.text = item.teamHome
            cityHome.text = item.cityHome
            conferenceHome.text = item.conferenceHome
            divisionHome.text = item.divisionHome
            scoreHome.text = item.scoreHome
            periodHome.text = item.periodHome
            seasonHome.text = item.seasonHome
            statusHome.text = item.statusHome

            teamVisitor.text = item.teamVisitor
            conferenceVisitor.text = item.conferenceVisitor
            cityVisitor.text = item.cityVisitor
            divisionVisitor.text = item.divisionVisitor
            scoreVisitor.text = item.scoreVisitor
        }
    }
}