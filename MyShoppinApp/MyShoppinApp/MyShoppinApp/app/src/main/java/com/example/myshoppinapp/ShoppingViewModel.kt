package com.example.myshoppinapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoopingViewModel(application :Application) : AndroidViewModel(application)
{
val allShoppingItems :LiveData<List<Shopping>>
val repository : ShoppingRepository

init {
    val dao = ShoppingDatabase.getDatabase(application).getItemsDao()
    repository = ShoppingRepository(dao)
    allShoppingItems = repository.AllShoppingItems
}

    fun addShoppingItem(items:Shopping) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(items)
    }
    fun deleteShoppingItem(items:Shopping) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(items)
    }
    fun updateShoppingItem(items:Shopping) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(items)
    }
     fun deleteAllShoppingItems() = viewModelScope.launch(Dispatchers.IO)
     {
        repository.deleteall()
    }

   // fun getAllItems() = repository.getAllItems()
}