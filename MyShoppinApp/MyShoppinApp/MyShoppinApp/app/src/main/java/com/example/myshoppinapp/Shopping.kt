package com.example.myshoppinapp


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="itemsTable")
 class Shopping ( @ColumnInfo(name="ItemName")
val ItemName :String,

@ColumnInfo(name="ItemDescription")
val ItemDescription :String,

@ColumnInfo(name="ItemCategory")
val ItemCategory :String,

@ColumnInfo(name="ItemPrice")
val ItemPrice :Int,
@ColumnInfo(name="ItemIsBought")
val ItemIsBought : Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var Id  = 0
}
