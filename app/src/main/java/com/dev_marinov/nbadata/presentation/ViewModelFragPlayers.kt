package com.dev_marinov.nbadata.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_marinov.nbadata.data.ObjectListPlayers
import com.dev_marinov.nbadata.model.RequestDataPlayers

class ViewModelFragPlayers : ViewModel() {

    private var hashMapPlayers: MutableLiveData<HashMap<Int, ObjectListPlayers>> = MutableLiveData()

    init {

        RequestDataPlayers.getData()
        hashMapPlayers.value = RequestDataPlayers.getPlayers()
    }

    fun getHashMapPlayers() = hashMapPlayers

}