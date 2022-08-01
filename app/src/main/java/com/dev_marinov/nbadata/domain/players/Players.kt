package com.dev_marinov.nbadata.domain.players

data class Players(
    var firstName: String,
    var lastName: String,
    var position: String?,
    var team: PlayersTeam,
)