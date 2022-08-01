package com.dev_marinov.nbadata.data.remote.dto.gamesdto

import com.dev_marinov.nbadata.domain.games.Games
import com.google.gson.annotations.SerializedName

data class GamesDTO(
    @SerializedName("date")
    val date: String,

    @SerializedName("home_team")
    val gamesHomeTeam: GamesHomeTeamDTO,

    @SerializedName("home_team_score")
    val scoreHome: String,
    @SerializedName("period")
    val periodHome: String,
    @SerializedName("season")
    val seasonHome: String,
    @SerializedName("status")
    val statusHome: String,

    @SerializedName("visitor_team")
    val gamesVisitorTeam: GamesVisitorTeamDTO,

    @SerializedName("visitor_team_score")
    val scoreVisitor: String
) {
    fun mapToDomain(): Games {
        return Games(
            date = date,
            gamesHomeTeam = gamesHomeTeam.mapToDomain(),
            scoreHome = scoreHome,
            periodHome = periodHome,
            seasonHome = seasonHome,
            statusHome = statusHome,
            gamesVisitorTeam = gamesVisitorTeam.mapToDomain()
        )
    }
}
