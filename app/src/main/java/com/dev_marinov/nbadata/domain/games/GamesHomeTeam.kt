package com.dev_marinov.nbadata.domain.games

import com.google.gson.annotations.SerializedName

data class GamesHomeTeam(
    val teamHome: String,
    val cityHome: String,
    val conferenceHome: String,
    val divisionHome: String
)
