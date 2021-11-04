package com.laupdev.projectx.data.database

import androidx.room.*

@Entity(
    tableName = "hotels_contact_info",
    foreignKeys = [ForeignKey(
        entity = Hotel::class,
        parentColumns = ["id"],
        childColumns = ["hotel_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["hotel_id"])]
)
data class ContactInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "hotel_id")
    val hotelId: Long,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "contacts")
    val contacts: String
)