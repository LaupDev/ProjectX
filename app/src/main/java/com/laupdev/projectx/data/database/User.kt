package com.laupdev.projectx.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index(
            value = ["email"],
            unique = true
        )
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "is_logged_in")
    val isLoggedIn: Int
)

@Entity
data class UserIsLoggedIn(
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "is_logged_in")
    val isLoggedIn: Int
)
