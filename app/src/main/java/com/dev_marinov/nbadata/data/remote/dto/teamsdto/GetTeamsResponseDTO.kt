package com.dev_marinov.nbadata.data.remote.dto.teamsdto

import com.google.gson.annotations.SerializedName

data class GetTeamsResponseDTO(
    @SerializedName("data")
    val data: List<TeamsDTO>
)