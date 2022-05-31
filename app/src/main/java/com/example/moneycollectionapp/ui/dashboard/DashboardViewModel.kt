package com.example.moneycollectionapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneycollectionapp.MoneyFriendMapping
import kotlinx.coroutines.launch

class DashboardViewModel(app: Application) : ViewModel() {
     private  val repo : FriendRepository
    lateinit var allFriends: LiveData<List<Friend>>
    init {
        repo= FriendRepository(app)
        allFriends = repo.getAllFriends()!!
    }

    fun getAllFriends () = viewModelScope.launch {
        repo.getAllFriends()
    }

    fun insertFriends(friend: Friend) = viewModelScope.launch {
        repo.insertStudent(friend)
    }
    fun updateFriends(friend: Friend) = viewModelScope.launch {
        repo.updateStudent(friend)
    }
    fun deleteFriends(friend: Friend) = viewModelScope.launch {
        repo.deleteStudent(friend)
    }

    fun getAllMoneyFriendMapping(friendID : Int): LiveData<List<MoneyFriendMapping>>? {
        return repo.getAllMoneyFriendMapping(friendID)
    }
}