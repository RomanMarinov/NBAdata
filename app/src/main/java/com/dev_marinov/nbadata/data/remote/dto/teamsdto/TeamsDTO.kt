package com.dev_marinov.nbadata.data.remote.dto.teamsdto

import com.dev_marinov.nbadata.domain.teams.Teams
import com.google.gson.annotations.SerializedName

data class TeamsDTO(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String
) {
    fun mapToDomain(): Teams {
        return Teams(
            fullName = fullName,
            city = city,
            conference = conference,
            division = division
        )
    }
}