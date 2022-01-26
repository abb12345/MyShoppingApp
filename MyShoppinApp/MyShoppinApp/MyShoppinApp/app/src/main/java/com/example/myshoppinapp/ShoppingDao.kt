package com.example.myshoppinapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : Shopping)

    @Update
    suspend fun update(item :Shopping)


    @Delete
    suspend fun Delete(item : Shopping)

    @Query("select * from itemsTable order by id ASC")
    fun getAllItems() :LiveData<List<Shopping>>

    @Query("DELETE FROM itemsTable")
    fun deleteAll()
}