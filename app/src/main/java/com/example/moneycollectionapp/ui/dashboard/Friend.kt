package com.example.moneycollectionapp.ui.dashboard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend")
data class Friend (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    var FriendId: Int? = null,
    @ColumnInfo(name = "f_name") var FName:String,
    @ColumnInfo(name = "l_name") var LName:String,
){}