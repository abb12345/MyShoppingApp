package com.example.myshoppinapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Shopping ::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getItemsDao() : ShoppingDao

    companion object
    {
        @Volatile
        private var INSTANCE :ShoppingDatabase? =null
        private val LOCK =Any()

        fun getDatabase(context: Context) : ShoppingDatabase
        {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "Shopping_Database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
        /*operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?:createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context :Context)=
            Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java,
                "Shopping.db").fallbackToDestructiveMigration().build()*/

    }
}