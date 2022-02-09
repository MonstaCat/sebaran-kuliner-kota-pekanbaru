package com.dikacandra.sebaran_kuliner_kota_pekanbaru.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Menu
import com.dikacandra.sebaran_kuliner_kota_pekanbaru.model.Restoran

//Database annotation to specify the entities and set version
@Database(entities = [Restoran::class, Menu::class], version = 2, exportSchema = false)
abstract class KulinerRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: KulinerRoomDatabase? = null

        fun getDatabase(context: Context): KulinerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KulinerRoomDatabase::class.java,
                    "kuliner_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getRestoranDao() : RestoranDao
    abstract fun getMenuDao() : MenuDao
}