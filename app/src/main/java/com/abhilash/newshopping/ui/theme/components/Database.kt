package com.abhilash.newshopping.ui.theme.components

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
@Database(entities = [ShopEntity::class, ItemEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDao
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Example: db.execSQL("ALTER TABLE ShopEntity ADD COLUMN newColumn TEXT")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shop_database"
                )
                    .addMigrations(MIGRATION_1_2)  // Add migration if necessary
                    .fallbackToDestructiveMigration()  // Optional: Use if you want to reset data on schema mismatch
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}



