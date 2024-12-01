package com.abhilash.newshopping.ui.theme.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction

data class Item(
    val name: String,   // Name of the item (e.g., rice, coriander)
    val quantity: Int? = null // Optional quantity field if needed
)

data class Shop(
    val shopName: String,  // Name of the shop
    val items: MutableList<Item> = mutableListOf() // List of items in the shop
)


@Entity
data class ShopEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val shopName: String
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = ShopEntity::class,
        parentColumns = ["id"],
        childColumns = ["shopId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val shopId: Int, // Foreign key to associate with ShopEntity
    val itemName: String,
    val itemQuantity: String
)


@Dao
interface ShopDao {
    @Transaction
    @Query("DELETE FROM ShopEntity WHERE id = :shopId")
    suspend fun deleteShopById(shopId: Int)
    @Insert
    suspend fun insertShop(shop: ShopEntity): Long

    @Insert
    suspend fun insertItems(items: List<ItemEntity>)

    @Query("SELECT * FROM ShopEntity")
    fun getAllShops(): LiveData<List<ShopEntity>>


    @Transaction
    @Query("SELECT * FROM ShopEntity")
    suspend fun getAllShopsWithItems(): List<ShopWithItems>

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Query("DELETE FROM ItemEntity WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Query("DELETE FROM ItemEntity WHERE itemName = :itemName")
    suspend fun deleteItemByName(itemName: String)
    @Query("DELETE FROM ItemEntity")
    suspend fun deleteAllItems()
}
@Dao
interface ItemDao {

    @Insert
    suspend fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM ItemEntity WHERE shopId = :shopId")
    fun getItemsByShopId(shopId: Int): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM ItemEntity")
    fun getAllItems(): LiveData<List<ItemEntity>>
}




data class ShopWithItems(
    @Embedded val shop: ShopEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopId"
    )
    val items: List<ItemEntity>
)
class ShopViewModelFactory(private val repository: ShopRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


