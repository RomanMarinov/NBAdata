package com.dev_marinov.nbadata.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev_marinov.nbadata.data.ObjectListGames
import com.dev_marinov.nbadata.model.RequestDataGames

class ViewModelFragGames : ViewModel() {

    private var hashMapGames: MutableLiveData<HashMap<Int, ObjectListGames>> = MutableLiveData()

//    var userList : MutableLiveData<HashMap<Int, User>> = MutableLiveData()

    //инициализируем список и заполняем его данными пользователей
    init {
        // с помощью value можно получить и отправить данные любым активным подписчикам
//        UserData.getData()
//        userList.value = UserData.getUsers()

        RequestDataGames.getData()
        hashMapGames.value = RequestDataGames.getGames()


    }

    fun getHashMapGames() = hashMapGames


//    fun getListUsers() = userList

//    //для обновления списка передаем второй список пользователей
//    fun updateListUsers() {
//        userList.value = UserData.getAnotherUsers()
//    }
}

