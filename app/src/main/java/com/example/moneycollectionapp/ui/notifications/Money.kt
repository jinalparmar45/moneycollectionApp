package com.example.moneycollectionapp.ui.notifications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "money")
data class Money(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "money_id")
    var MoneyId: Int? = null,
    @ColumnInfo(name = "amount") var Amount:Int,
    @ColumnInfo(name = "type") var Type:String,
    @ColumnInfo(name = "Shared_in") var SharedIn:Int,
    @ColumnInfo(name= "date") var Date: String,
)
