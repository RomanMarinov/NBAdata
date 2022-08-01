package com.dev_marinov.nbadata.data.remote.dto.gamesdto

import com.dev_marinov.nbadata.domain.games.GamesVisitorTeam
import com.google.gson.annotations.SerializedName

data class GamesVisitorTeamDTO(
    @SerializedName("full_name")
    val teamVisitor: String,
    @SerializedName("conference")
    val conferenceVisitor: String,
    @SerializedName("city")
    val cityVisitor: String,
    @SerializedName("division")
    val divisionVisitor: String
) {
    fun mapToDomain() : GamesVisitorTeam{
        return GamesVisitorTeam(
            teamVisitor = teamVisitor,
            conferenceVisitor = conferenceVisitor,
            cityVisitor = cityVisitor,
            divisionVisitor = divisionVisitor
        )
    }
}
