package com.dev_marinov.nbadata.data.remote.dto.playersdto

import com.google.gson.annotations.SerializedName

data class GetPlayersResponseDTO(
    @SerializedName("data")
    val data: List<PlayersDTO>
)
