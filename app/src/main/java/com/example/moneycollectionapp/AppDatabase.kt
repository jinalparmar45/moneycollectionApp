package com.example.moneycollectionapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moneycollectionapp.ui.dashboard.Friend
import com.example.moneycollectionapp.ui.dashboard.FriendDAO
import com.example.moneycollectionapp.ui.notifications.Money
import com.example.moneycollectionapp.ui.notifications.MoneyDao
import com.example.moneycollectionapp.ui.notifications.MoneyFriendMappingDAO

@Database(entities = [Money::class, Friend::class, MoneyFriendMapping::class],version = 1,exportSchema = false )
abstract class AppDatabase :RoomDatabase() {

    abstract fun FriendDAO() : FriendDAO
    abstract fun MoneyDao() : MoneyDao
    abstract fun MoneyFriendMappingDAO(): MoneyFriendMappingDAO

    companion object{
        var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {

            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    //aquiring instancce of room DB
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "studen.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE;
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}