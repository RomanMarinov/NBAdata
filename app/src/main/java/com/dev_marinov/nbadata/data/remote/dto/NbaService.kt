package com.dev_marinov.nbadata.data.remote.dto

import com.dev_marinov.nbadata.data.remote.dto.gamesdto.GetGamesResponseDTO
import com.dev_marinov.nbadata.data.remote.dto.playersdto.GetPlayersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

    val default_page = "0"
    val default_per_page = "25"

    interface NbaService {

    //     val baseUrl = "https://free-nba.p.rapidapi.com/games?page=0&per_page=25"

        @GET("games")
    suspend fun getGames(
        @Query("page") page: String = default_page,
        @Query("per_page") per_page: String = default_per_page
    ) : Response<GetGamesResponseDTO>

        @GET("games")
    suspend fun getPlayers(
            @Query("page") page: String = default_page,
            @Query("per_page") per_page: String = default_per_page
    ) : Response<GetPlayersResponseDTO>

        @GET("games")
    suspend fun getTeams(
            @Query("page") page: String = default_page,
            @Query("per_page") per_page: String = default_per_page
    ) : Response<GetGamesResponseDTO>
}