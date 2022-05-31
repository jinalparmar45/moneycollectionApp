package com.example.moneycollectionapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MoneyDao {
    @Insert
    fun insertMoney(Money : Money)

    //read
    @Query("select * from money")
    fun getAllMoney(): LiveData<List<Money>>


    //update
    @Update
    fun updateMoney(money: Money)

    //delete
    @Delete
    fun deleteMoney(money: Money)

}