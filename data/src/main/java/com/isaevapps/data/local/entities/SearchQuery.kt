package com.isaevapps.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_history")
data class SearchQuery(
    @PrimaryKey val query: String,
    val timestamp: Long = System.currentTimeMillis()
)

