package com.laupdev.projectx.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "hotels"
)
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "main_image_path")
    val imagePath: String
)
