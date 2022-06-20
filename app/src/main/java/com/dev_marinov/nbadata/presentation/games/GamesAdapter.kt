package com.dev_marinov.nbadata.presentation.games

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.nbadata.R
import com.dev_marinov.nbadata.data.ObjectListGames
import com.dev_marinov.nbadata.databinding.ItemGamesBinding

class GamesAdapter() : RecyclerView.Adapter<GamesAdapter.ViewHolder>(){

    private var hashMap: HashMap<Int, ObjectListGames> = HashMap()
    private lateinit var context: Context // только для анимации views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemGamesBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashMap[position]!!)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshGames(hashMap: HashMap<Int, ObjectListGames>) {
        this.hashMap = hashMap
        notifyDataSetChanged()
    }

    // ДЛЯ ЧЕГО INNER
    inner class ViewHolder(
        private val binding: ItemGamesBinding,
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(objectListGames: ObjectListGames) {

            binding.games = objectListGames


            //binding.games.teamHome
                //.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация

            // установка анимации binding.tvGamesDate.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesNameTeam.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesCity.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesConference.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesDivisionHome.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesScore.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesPeriod.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesSeason.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesStatus.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация

            binding.tvGamesNameVisitor.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesConferenceVisitor.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesCityVisitor.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesDivisionVisitor.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            binding.tvGamesScoreVisitor.animation = AnimationUtils.loadAnimation(context, R.anim.scale_up_1) // анимация
            // Метод executePendingBindings используется, чтобы биндинг не откладывался,
            // а выполнился как можно быстрее. Это критично в случае с RecyclerView.

            binding.executePendingBindings()

        }
    }
}