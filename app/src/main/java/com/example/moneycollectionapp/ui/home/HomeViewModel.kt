package com.example.moneycollectionapp.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneycollectionapp.ui.notifications.Money
import com.example.moneycollectionapp.ui.notifications.MoneyRepo
import kotlinx.coroutines.launch

class HomeViewModel(app : Application) : ViewModel() {

    private  val repo : MoneyRepo
    lateinit var allMoney: LiveData<List<Money>>
    init {
        repo= MoneyRepo(app)
        allMoney = repo.getAllSpendings()!!
    }


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Spendings Fragment"
//    }
//    val text: LiveData<String> = _text
}