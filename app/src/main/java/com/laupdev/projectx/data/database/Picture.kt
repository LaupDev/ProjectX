package com.laupdev.projectx.data.database

import androidx.room.*

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
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "hotel_id")
    val hotel_id: Int,
    @ColumnInfo(name = "image_title")
    val imageTitle: String,
    @ColumnInfo(name = "image_path")
    val imagePath: String
)
