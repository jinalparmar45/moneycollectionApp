package com.example.moneycollectionapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moneycollectionapp.ui.dashboard.Friend

@Dao
interface FriendDAO {
    @Insert
    fun insertSFriend(friend : Friend)

    //read
    @Query("select * from friend")
    fun getAllFriends(): LiveData<List<Friend>>


    //update
    @Update
    fun updateFriends(friend: Friend)

    //delete
    @Delete
    fun deleteFriend(friend: Friend)
}