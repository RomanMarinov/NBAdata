package com.dev_marinov.nbadata.domain.games

data class Games (

    var date: String,

    var gamesHomeTeam: GamesHomeTeam,

    var scoreHome: String,
    var periodHome: String,
    var seasonHome: String,
    var statusHome: String,

    var gamesVisitorTeam: GamesVisitorTeam

)