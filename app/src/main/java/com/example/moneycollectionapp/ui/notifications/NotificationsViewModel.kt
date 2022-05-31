package com.example.moneycollectionapp.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneycollectionapp.MoneyFriendMapping
import kotlinx.coroutines.launch

class NotificationsViewModel(app : Application) : ViewModel() {
    private  val repo : MoneyRepo
    //lateinit var allMoney: LiveData<List<Money>>
    init {
        repo= MoneyRepo(app)
       // allMoney = repo.getAllSpendings()!!
    }

    fun getAllMoney (): LiveData<List<Money>>? {
        return repo.getAllSpendings()
    }

    fun insertSpending(Money : Money) = viewModelScope.launch {
        repo.insertSpending(Money)
    }
    fun updateSpending(Money : Money) = viewModelScope.launch {
        repo.updateSpending(Money)
    }
    fun deleteSpending(Money : Money) = viewModelScope.launch {
        repo.deleteSpending(Money)
    }

    fun insertMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping) = viewModelScope.launch{
        repo.insertMoneyFriendMapping(MoneyFriendMapping)
    }
    fun updateMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping) = viewModelScope.launch{
        repo.updateMoneyFriendMapping(MoneyFriendMapping)
    }
    fun deleteMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping) = viewModelScope.launch{
        repo.deleteMoneyFriendMapping(MoneyFriendMapping)
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }
//    val text: LiveData<String> = _text
}