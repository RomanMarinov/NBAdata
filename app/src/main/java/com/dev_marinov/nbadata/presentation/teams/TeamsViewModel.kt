package com.dev_marinov.nbadata.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.data.ObjectListTeams
import com.dev_marinov.nbadata.model.teams.RequestDataTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*

class TeamsViewModel : ViewModel() {

    private var _teams: MutableLiveData<HashMap<Int, ObjectListTeams>> = MutableLiveData()
    val teams: LiveData<HashMap<Int, ObjectListTeams>> = _teams

    init {
        getTeams()
    }

    private fun getTeams(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://free-nba.p.rapidapi.com/teams?page=0")
            .get()
            .addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "3f235a9f12msh754db7d5868e472p168e1djsn3c41eccf8098")
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            val result = RequestDataTeams.getHashMap(client, request)
            _teams.postValue(result)
        }
    }
}