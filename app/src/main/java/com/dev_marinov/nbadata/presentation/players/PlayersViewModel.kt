package com.dev_marinov.nbadata.presentation.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.data.ObjectListPlayers
import com.dev_marinov.nbadata.model.players.RequestDataPlayers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*

class PlayersViewModel : ViewModel() {

    private val _players: MutableLiveData<HashMap<Int, ObjectListPlayers>> = MutableLiveData()
    val players: LiveData<HashMap<Int, ObjectListPlayers>> = _players

    init {
        getPlayers()
    }

    private fun getPlayers() {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/players?page=0&per_page=25")
            .get()
            .addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098")
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            val result = RequestDataPlayers.getHashMap(client, request)
            _players.postValue(result)
        }

    }
}