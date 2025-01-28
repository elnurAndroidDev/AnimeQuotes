package com.isaevapps.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

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