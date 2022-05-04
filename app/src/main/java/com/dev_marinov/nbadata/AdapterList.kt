package com.dev_marinov.nbadata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView


class AdapterList(val requireActivity: FragmentActivity, var hashMap: HashMap<Int, ObjectList>, recyclerView: RecyclerView)
    : RecyclerView.Adapter<AdapterList.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        var objectList = hashMap[position]

        if(objectList != null) {
            holder.fullName.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.city.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.conference.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.division.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация
            holder.position.animation = AnimationUtils.loadAnimation(requireActivity, R.anim.scale_up_1) // анимация

            holder.fullName.setText(objectList.fullName.toString())
            holder.city.setText(objectList.city.toString())
            holder.conference.setText(objectList.conference.toString())
            holder.division.setText(objectList.division.toString())

            if (!objectList.position.toString().equals("")) {
                holder.position.setText(objectList.position.toString())
            } else {
                holder.position.setText("n/f")
            }
        }
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var fullName: TextView = itemView.findViewById<TextView>(R.id.tvFullName)
        var city: TextView = itemView.findViewById<TextView>(R.id.tvCity)
        var conference: TextView = itemView.findViewById<TextView>(R.id.tvConference)
        var division: TextView = itemView.findViewById<TextView>(R.id.tvDivision)
        var position: TextView = itemView.findViewById<TextView>(R.id.tvPosition)
    }
}