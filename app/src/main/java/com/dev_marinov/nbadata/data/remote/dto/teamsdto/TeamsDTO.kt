package com.dev_marinov.nbadata.data.remote.dto.teamsdto

import com.google.gson.annotations.SerializedName

data class TeamsDTO(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String
)
