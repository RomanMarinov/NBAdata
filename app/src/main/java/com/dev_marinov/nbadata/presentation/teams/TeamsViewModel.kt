package com.dev_marinov.nbadata.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.nbadata.domain.INbaRepository
import com.dev_marinov.nbadata.domain.teams.Teams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(private val iNbaRepository: INbaRepository) : ViewModel() {

    private var _teams: MutableLiveData<List<Teams>> = MutableLiveData()
    val teams: LiveData<List<Teams>> = _teams

    init {
        getTeams()
    }

    private fun getTeams() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = iNbaRepository.getTeams()
            result?.let {
                _teams.postValue(it)
            }
        }
    }
}