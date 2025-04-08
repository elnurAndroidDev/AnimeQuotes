package com.isaevapps.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaevapps.data.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_table WHERE animeId = :animeId LIMIT 1)")
    suspend fun isFavorite(animeId: Int): Boolean

    @Query("DELETE FROM favorite_table WHERE animeId = :animeId")
    suspend fun deleteFavorite(animeId: Int)
}