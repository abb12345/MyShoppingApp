package com.example.myshoppinapp

import androidx.lifecycle.LiveData

class ShoppingRepository(private val  shoppingDao : ShoppingDao) {

    val AllShoppingItems : LiveData<List<Shopping>> = shoppingDao.getAllItems()
    //fun getAllItems() = db.getItemsDao().getAllItems()

    suspend fun insert(items: Shopping) {
        shoppingDao.insert(items)
    }
    suspend fun delete(items: Shopping) {

        shoppingDao.Delete(items)
    }

    suspend fun deleteall() {

        shoppingDao.deleteAll()
    }

suspend fun update(items: Shopping) {
    shoppingDao.update(items)
}



}