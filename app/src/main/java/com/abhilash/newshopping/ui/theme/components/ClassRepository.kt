package com.abhilash.newshopping.ui.theme.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ShopRepository(private val shopDao: ShopDao) {

    // Function to add a shop to the database
    suspend fun addShop(shopName: String): Long {
        val shop = ShopEntity(shopName = shopName)
        return shopDao.insertShop(shop)
    }
    suspend fun deleteShopById(shopId: Int) {
        shopDao.deleteShopById(shopId)
    }

    // Add a new item
    suspend fun addItem(item: ItemEntity) {
        shopDao.insertItems(listOf(item))
    }

    // Remove a single item
    suspend fun removeItem(item: ItemEntity) {
        shopDao.deleteItem(item)
    }

    suspend fun removeItemById(itemId: Int) {
        shopDao.deleteItemById(itemId)
    }

    suspend fun removeItemByName(itemName: String) {
        shopDao.deleteItemByName(itemName)
    }

    // Remove all items
    suspend fun clearAllItems() {
        shopDao.deleteAllItems()
    }

    // LiveData to observe the list of all shops
    val shopList: LiveData<List<ShopEntity>> = shopDao.getAllShops()
}

//class ShopViewModel(private val repository: ShopRepository) : ViewModel() {
//
//    // Add a new item
//    fun addItem(item: ItemEntity) {
//        viewModelScope.launch {
//            repository.addItem(item)
//        }
//    }
//
//    // Remove a single item
//    fun removeItem(item: ItemEntity) {
//        viewModelScope.launch {
//            repository.removeItem(item)
//        }
//    }
//
//    fun removeItemById(itemId: Int) {
//        viewModelScope.launch {
//            repository.removeItemById(itemId)
//        }
//    }
//
//    fun removeItemByName(itemName: String) {
//        viewModelScope.launch {
//            repository.removeItemByName(itemName)
//        }
//    }
//
//    // Remove all items
//    fun clearAllItems() {
//        viewModelScope.launch {
//            repository.clearAllItems()
//        }
//    }
//    fun insertShop(shopName: String) {
//        viewModelScope.launch {
//            repository.addShop(shopName)
//        }
//    }
//}



class ShopViewModel(private val repository: ShopRepository) : ViewModel() {

    // Use the shopList from the repository directly
    val shopList: LiveData<List<ShopEntity>> = repository.shopList

    // Function to insert a new shop
    fun insertShop(shopName: String) {
        viewModelScope.launch {
            repository.addShop(shopName)
        }
    }

    fun deleteShopById(shopId: Int) {
        viewModelScope.launch {
            repository.deleteShopById(shopId)
        }
    }
}



