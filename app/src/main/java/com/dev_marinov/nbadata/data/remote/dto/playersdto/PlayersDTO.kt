package com.dev_marinov.nbadata.data.remote.dto.playersdto

import com.dev_marinov.nbadata.domain.players.Players
import com.google.gson.annotations.SerializedName

data class PlayersDTO(
    @SerializedName("first_name")
    val fullName: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("position")
    val position: String?,
    @SerializedName("team")
    val playersTeamDTO: PlayersTeamDTO
) {
    fun mapToDomain() : Players {
        return Players(
            firstName = fullName,
            lastName = last_name,
            position = position,
            team = playersTeamDTO.mapToDomain()
        )
    }
}
