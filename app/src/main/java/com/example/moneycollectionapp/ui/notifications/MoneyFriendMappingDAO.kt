package com.example.moneycollectionapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moneycollectionapp.MoneyFriendMapping

@Dao
interface MoneyFriendMappingDAO {
    @Insert
    fun insertMoneyFriendMapping(MoneyFriendMapping: MoneyFriendMapping)

    //read
    @Query("select * from money_friend_mapping where friend_id = :friendID")
    fun getAllMoneyFriendMapping( friendID: Int): LiveData<List<MoneyFriendMapping>>


    //update
    @Update
    fun updateMoneyFriendMapping(MoneyFriendMapping: MoneyFriendMapping)

    //delete
    @Delete
    fun deleteMoneyFriendMapping(MoneyFriendMapping: MoneyFriendMapping)
}