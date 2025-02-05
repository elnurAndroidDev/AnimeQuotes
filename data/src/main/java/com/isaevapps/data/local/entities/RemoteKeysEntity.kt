package com.isaevapps.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevKey: Int?,
    val nextKey: Int?
)
