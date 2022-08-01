package com.dev_marinov.nbadata.data.remote.dto.gamesdto

import com.google.gson.annotations.SerializedName

data class GetGamesResponseDTO(
    @SerializedName("data")
    val data: List<GamesDTO>
)
