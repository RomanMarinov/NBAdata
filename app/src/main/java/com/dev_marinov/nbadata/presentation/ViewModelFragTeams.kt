package com.dev_marinov.nbadata.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_marinov.nbadata.data.ObjectListTeams
import com.dev_marinov.nbadata.model.RequestDataTeams

class ViewModelFragTeams : ViewModel() {

    private var hashMapTeams: MutableLiveData<HashMap<Int, ObjectListTeams>> = MutableLiveData()

    init {
        RequestDataTeams.getData()
        hashMapTeams.value = RequestDataTeams.getTeams()
    }

    fun getHashMapTeams() = hashMapTeams
}