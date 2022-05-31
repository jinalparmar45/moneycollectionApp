package com.example.moneycollectionapp.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.moneycollectionapp.AppDatabase
import com.example.moneycollectionapp.MoneyFriendMapping
import com.example.moneycollectionapp.ui.notifications.MoneyFriendMappingDAO

class FriendRepository(context: Context) {
    var db: FriendDAO? = AppDatabase.getInstance(context)?.FriendDAO()
    var db2: MoneyFriendMappingDAO? = AppDatabase.getInstance(context)?.MoneyFriendMappingDAO()
    fun getAllFriends() : LiveData<List<Friend>>? {
        return db?.getAllFriends()
    }

    fun insertStudent(Friend: Friend){
        db?.insertSFriend(Friend)
    }

    fun updateStudent(Friend: Friend){
        db?.updateFriends(Friend)
    }

    fun deleteStudent(Friend: Friend){
        db?.deleteFriend(Friend)
    }

    fun getAllMoneyFriendMapping(friendID : Int) : LiveData<List<MoneyFriendMapping>>? {
        return db2?.getAllMoneyFriendMapping(friendID)
    }
}