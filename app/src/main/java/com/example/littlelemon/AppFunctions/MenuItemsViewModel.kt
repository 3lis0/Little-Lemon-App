package com.example.littlelemon.AppFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.littlelemon.Database.BasketList
import com.example.littlelemon.Database.MenuJsonList

class MenuItemsViewModel : ViewModel() {

    // Private mutable list of MenuItemsJson
    private val _menuItems = MutableLiveData<List<BasketList>>()

    // Public LiveData for observing the menu items
    val menuItems: LiveData<List<BasketList>> get() = _menuItems

    // Function to set/update the list of menu items
    fun setMenuItems(items: List<BasketList>) {
        _menuItems.value = items
    }

    // Function to add or update a single item in the list
    fun addMenuItem(newItem: BasketList) {
        val updatedList = _menuItems.value?.map { item ->
            if (item.id == newItem.id) {
                // If the item already exists, update its amount
                item.copy(amount = item.amount + newItem.amount)
            } else {
                item
            }
        }?.toMutableList() ?: mutableListOf()

        // If the item was not found, add it to the list
        if (updatedList.none { it.id == newItem.id }) {
            updatedList.add(newItem)
        }

        _menuItems.value = updatedList
    }

    fun updateItemAmountById(id: Int, newAmount: Int) {
        _menuItems.value = _menuItems.value?.map { item ->
            if (item.id == id) {
                item.copy(amount = newAmount)
            } else {
                item
            }
        }
    }

    // Function to delete an item from the list by id
    fun deleteItemById(id: Int) {
        _menuItems.value = _menuItems.value?.filterNot { it.id == id }
    }

    // Function to clear the list
    fun clearMenuItems() {
        _menuItems.value = emptyList()
    }
}