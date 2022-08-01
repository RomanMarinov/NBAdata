package com.dev_marinov.nbadata.presentation.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.domain.INbaRepository
import com.dev_marinov.nbadata.domain.players.Players
import com.dev_marinov.nbadata.model.players.RequestDataPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(private val iNbaRepository: INbaRepository): ViewModel() {

    private val _players: MutableLiveData<List<Players>> = MutableLiveData()
    val players: LiveData<List<Players>> = _players

    init {
        getPlayers()
    }

    private fun getPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = iNbaRepository.getPlayers()
            result?.let {
                _players.postValue(it)
            }
        }

    }
}