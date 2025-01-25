package com.abhilash.newshopping.ui.theme.components

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ShopRepository(
    private val shopDao: ShopDao,
    private val itemDao: ItemDao) {


    suspend fun addItemToShopByName(
        shopName: String,
        itemName: String,
        itemQuantity: String,
        price: Double
    ) {
        val shopId = shopDao.getShopIdByName(shopName)
        if (shopId != null) {
            val item = ItemEntity(
                shopId = shopId,
                itemName = itemName,
                itemQuantity = itemQuantity,
                itemPrice = price
            )
            itemDao.insertItem(item)
        } else {
            throw IllegalArgumentException("Shop with name '$shopName' not found")
        }
    }

    suspend fun getItemsByShopName(shopName: String): List<ItemEntity> {
        val shopId = shopDao.getShopIdByName(shopName) ?: return emptyList()
        return itemDao.getItemsByShopIdSync(shopId)
    }
    suspend fun removetheItemByName(itemName: String) {
        itemDao.deleteItemByName(itemName)
    }




    suspend fun getAllItemsSync(): List<Item> {
        return itemDao.getAllItemsSync().map { entity ->
            Item(
                name = entity.itemName,
                quantity = entity.itemQuantity.toInt(),
                price = entity.itemPrice
            )
        }
    }



    suspend fun addShop(shopName: String): Long {
        val shop = ShopEntity(shopName = shopName)
        return shopDao.insertShop(shop)
    }

    // Item-related functions
    suspend fun addItem(item: ItemEntity) {
        itemDao.insertItem(item)
    }
    suspend fun deleteShopById(shopId: Int) {
        shopDao.deleteShopById(shopId)
    }

    // Add a new item
    suspend fun addItems(item: ItemEntity) {
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


class ShopViewModel(private val repository: ShopRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Item>>(emptyList())
    val items: LiveData<List<Item>> get() = _items

    init {
        viewModelScope.launch {
            try {
                // Fetch items from the repository and update LiveData
                val itemList = repository.getAllItemsSync()
                _items.postValue(itemList)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error fetching items: ${e.message}")
            }
        }
    }

    fun removeItemByName(itemName: String) {
        viewModelScope.launch {
            try {
                // Remove the item from the database
                repository.removeItemByName(itemName)

                // Update the local LiveData to reflect the changes
                _items.value = _items.value?.filter { it.name != itemName }
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error removing item: ${e.message}")
            }
        }
    }

    val shopList: LiveData<List<ShopEntity>> = repository.shopList

    fun insertShop(shopName: String) {
        viewModelScope.launch {
            try {
                repository.addShop(shopName)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error inserting shop: ${e.message}")
            }
        }
    }

    fun deleteShopById(shopId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteShopById(shopId)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error deleting shop: ${e.message}")
            }
        }
    }

    fun addItem(item: ItemEntity) {
        viewModelScope.launch {
            try {
                repository.addItem(item)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error adding item: ${e.message}")
            }
        }
    }

    fun addItemToShopByName(
        shopName: String,
        itemName: String,
        itemQuantity: String,
        price: Double = 0.0
    ) {
        viewModelScope.launch {
            try {
                repository.addItemToShopByName(shopName, itemName, itemQuantity, price)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error adding item to shop: ${e.message}")
            }
        }
    }

    fun getItemsByShopName(shopName: String): LiveData<List<ItemEntity>> {
        val liveData = MutableLiveData<List<ItemEntity>>()
        viewModelScope.launch {
            try {
                val items = repository.getItemsByShopName(shopName)
                liveData.postValue(items)
            } catch (e: Exception) {
                Log.e("ShopViewModel", "Error fetching items by shop name: ${e.message}")
            }
        }
        return liveData
    }
}


//
//class ShopViewModel(private val repository: ShopRepository) : ViewModel() {
//    private val _items = MutableLiveData<List<Item>>(listOf())
//    val items: LiveData<List<Item>> get() = _items
//
//    init {
//        viewModelScope.launch {
//            val items = repository.getAllItemsSync()
//            _items.getAllItemSync(items) // Ensure this works
//        }
//    }
//
//
//    fun removetheItemByName(itemName: String) {
//        viewModelScope.launch {
//            try {
//                // Remove the item from the database
//                repository.removeItemByName(itemName)
//
//                // Update the local LiveData to reflect the changes
//                _items.value = _items.value?.filter { it.name != itemName }
//            } catch (e: Exception) {
//                Log.e("ShopViewModel", "Error removing item: ${e.message}")
//            }
//        }
//    }
//
//
//
//    fun removeItemByName(itemName: String) {
//        _items.value = _items.value?.filter { it.name != itemName }
//    }
//
//    // Use the shopList from the repository directly
//    val shopList: LiveData<List<ShopEntity>> = repository.shopList
//
//    // Function to insert a new shop
//    fun insertShop(shopName: String) {
//        viewModelScope.launch {
//            repository.addShop(shopName)
//        }
//    }
//
//    fun deleteShopById(shopId: Int) {
//        viewModelScope.launch {
//            repository.deleteShopById(shopId)
//        }
//    }
//    fun addItem(item: ItemEntity) {
//        viewModelScope.launch {
//            repository.addItem(item)
//        }
//    }
//
//
//    fun addItemToShopByName(shopName: String, itemName: String, itemQuantity: String,price:Double=0.0) {
//        viewModelScope.launch {
//            try {
//                repository.addItemToShopByName(shopName, itemName, itemQuantity,price)
//            } catch (e: Exception) {
//                Log.e("ShopViewModel", "Error adding item: ${e.message}")
//            }
//        }
//    }
//
//    fun getItemsByShopName(shopName: String): LiveData<List<ItemEntity>> {
//        val liveData = MutableLiveData<List<ItemEntity>>()
//        viewModelScope.launch {
//            val items = repository.getItemsByShopName(shopName)
//            liveData.postValue(items)
//        }
//        return liveData
//    }
//
//
//
//
//}



