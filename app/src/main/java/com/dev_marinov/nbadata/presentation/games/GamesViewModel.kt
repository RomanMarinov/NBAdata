package com.dev_marinov.nbadata.presentation.games

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.data.ObjectListGames
import com.dev_marinov.nbadata.model.games.RequestDataGames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*

class GamesViewModel : ViewModel() {

    private val _games: MutableLiveData<HashMap<Int, ObjectListGames>> = MutableLiveData()
    val games: LiveData<HashMap<Int, ObjectListGames>> = _games

    init {
        getGames()
    }

    private fun getGames() {
        Log.e("333", "-getGames=")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/games?page=0&per_page=25")
            .get()
            .addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098")
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            Log.e("333", "- viewModelScope.launch=")

            val result = RequestDataGames.getHashMap(client, request)
            _games.postValue(result)

        }

    }

}

