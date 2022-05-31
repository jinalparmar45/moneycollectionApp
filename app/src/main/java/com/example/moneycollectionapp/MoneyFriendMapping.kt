package com.example.moneycollectionapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.moneycollectionapp.ui.dashboard.Friend
import com.example.moneycollectionapp.ui.notifications.Money

@Entity(tableName = "money_friend_mapping", foreignKeys = [
    ForeignKey(entity = Money::class,
        parentColumns = ["money_id"],
        childColumns = ["money_id"],
        onDelete = CASCADE),
    ForeignKey(entity = Friend::class,
        parentColumns = ["friend_id"],
        childColumns = ["friend_id"],
        onDelete = CASCADE)])

data class MoneyFriendMapping(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fm_mapping_id")
    var FMMapId: Int? = null,

    @ColumnInfo(name = "friend_id") var FriendId : Int,
    @ColumnInfo(name = "money_id") var MoneyId : Int,
    @ColumnInfo(name = "shared_amount") var SharedAmt : Float,
    @ColumnInfo(name = "type") var Type : String
)
//@Entity(tableName = "Foo",
//    foreignKeys = [
//        ForeignKey(entity = Money::class,
//            parentColumns = ["someCol"],
//            childColumns = ["someOtherCol"],
//            onDelete = CASCADE)])
