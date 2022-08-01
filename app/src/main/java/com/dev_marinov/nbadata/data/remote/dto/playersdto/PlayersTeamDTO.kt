package com.dev_marinov.nbadata.data.remote.dto.playersdto

import com.dev_marinov.nbadata.domain.players.PlayersTeam
import com.google.gson.annotations.SerializedName

data class PlayersTeamDTO(
    @SerializedName("city")
    val city: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String
) {
    fun mapToDomain() : PlayersTeam {
        return PlayersTeam(
            city = city,
            conference = conference,
            division = division
        )
    }
}
