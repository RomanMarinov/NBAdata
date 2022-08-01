package com.dev_marinov.nbadata.data.remote.dto.gamesdto

import com.dev_marinov.nbadata.domain.games.GamesHomeTeam
import com.google.gson.annotations.SerializedName

data class GamesHomeTeamDTO(
    @SerializedName("full_name")
    val teamHome: String,
    @SerializedName("city")
    val cityHome: String,
    @SerializedName("conference")
    val conferenceHome: String,
    @SerializedName("division")
    val divisionHome: String
) {
    fun mapToDomain(): GamesHomeTeam {
        return GamesHomeTeam(
            teamHome = teamHome,
            cityHome = cityHome,
            conferenceHome = conferenceHome,
            divisionHome = divisionHome
        )
    }
}
