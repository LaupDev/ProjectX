package com.laupdev.projectx.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "Gallery",
    foreignKeys = [
        ForeignKey(
            entity = Hotel::class,
            parentColumns = ["id"],
            childColumns = ["hotel_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["hotel_id"])
    ]
)
data class Picture(
    @ColumnInfo(name = "hotel_id")
    val hotel_id: Int,
    @ColumnInfo(name = "image_title")
    val imageTitle: String,
    @ColumnInfo(name = "image_path")
    val imagePath: String
)
