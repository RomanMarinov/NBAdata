package com.dev_marinov.nbadata.domain

import com.dev_marinov.nbadata.domain.games.Games
import com.dev_marinov.nbadata.domain.players.Players

interface INbaRepository {

    suspend fun getPlayers() : List<Players>
    suspend fun getTeams() : List<Teams>
    suspend fun getGames() : List<Games>
}