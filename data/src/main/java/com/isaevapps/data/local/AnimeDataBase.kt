package com.isaevapps.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.entities.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimeDataBase: RoomDatabase() {

    abstract fun animeDao(): AnimeDao

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