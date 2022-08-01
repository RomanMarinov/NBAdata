package com.dev_marinov.nbadata.data.remote

import com.dev_marinov.nbadata.data.remote.dto.NbaService
import com.dev_marinov.nbadata.domain.games.Games
import com.dev_marinov.nbadata.domain.INbaRepository
import com.dev_marinov.nbadata.domain.players.Players
import com.dev_marinov.nbadata.domain.teams.Teams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NbaRepository @Inject constructor(private val nbaService: NbaService) : INbaRepository {
    override suspend fun getPlayers(): List<Players> {
        val response = nbaService.getPlayers()
        return response.body()?.let {
            it.data.map { PlayersDTO ->
                PlayersDTO.mapToDomain()
            }
        } ?: listOf()
    }

    override suspend fun getTeams(): List<Teams> {
        val response = nbaService.getTeams()
        return response.body()?.let {
            it.data.map { TeamsDTO ->
                TeamsDTO.mapToDomain()
            }
        } ?: listOf()
    }

    override suspend fun getGames(): List<Games> {
        val response = nbaService.getGames()
        return response.body()?.let {
            it.data.map { GamesDTO ->
                GamesDTO.mapToDomain()
            }
        } ?: listOf()

    }
}