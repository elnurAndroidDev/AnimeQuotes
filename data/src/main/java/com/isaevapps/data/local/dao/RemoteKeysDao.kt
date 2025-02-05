package com.isaevapps.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaevapps.data.local.entities.RemoteKeysEntity


@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys ORDER BY rowid DESC LIMIT 1;")
    suspend fun getLastRemoteKey(): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: RemoteKeysEntity)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()
}