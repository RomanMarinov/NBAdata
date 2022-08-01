package com.dev_marinov.nbadata.presentation.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.domain.games.Games
import com.dev_marinov.nbadata.domain.INbaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val iNbaRepository: INbaRepository) : ViewModel() {

    private val _games: MutableLiveData<List<Games>> = MutableLiveData()
    val games: LiveData<List<Games>> = _games

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = iNbaRepository.getGames()
            result?.let {
            _games.postValue(it)
            }
        }

    }

}

