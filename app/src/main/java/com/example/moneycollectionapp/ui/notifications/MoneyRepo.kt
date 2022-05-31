package com.example.moneycollectionapp.ui.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.moneycollectionapp.AppDatabase
import com.example.moneycollectionapp.MoneyFriendMapping


class MoneyRepo(context: Context) {
    var db: MoneyDao? = AppDatabase.getInstance(context)?.MoneyDao()
    var db2: MoneyFriendMappingDAO ? = AppDatabase.getInstance(context)?.MoneyFriendMappingDAO()
    fun getAllSpendings() : LiveData<List<Money>>? {
        return db?.getAllMoney()
    }

    fun insertSpending(Money : Money){
        db?.insertMoney(Money)
    }

    fun updateSpending(Money : Money){
        db?.updateMoney(Money)
    }

    fun deleteSpending(Money : Money){
        db?.deleteMoney(Money)
    }



    fun insertMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping){
        db2?.insertMoneyFriendMapping(MoneyFriendMapping)
    }

    fun updateMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping){
        db2?.updateMoneyFriendMapping(MoneyFriendMapping)
    }

    fun deleteMoneyFriendMapping(MoneyFriendMapping : MoneyFriendMapping){
        db2?.deleteMoneyFriendMapping(MoneyFriendMapping)
    }

}