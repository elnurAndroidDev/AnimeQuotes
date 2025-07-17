package com.isaevapps.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.dao.FavoriteDao
import com.isaevapps.data.local.dao.SearchHistoryDao
import com.isaevapps.data.local.entities.AnimeEntity
import com.isaevapps.data.local.entities.FavoriteEntity
import com.isaevapps.data.local.entities.SearchQuery

@Database(entities = [AnimeEntity::class, FavoriteEntity::class, SearchQuery::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimeDataBase: RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var Instance: AnimeDataBase? = null

        fun getDatabase(context: Context): AnimeDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AnimeDataBase::class.java, "anime_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}