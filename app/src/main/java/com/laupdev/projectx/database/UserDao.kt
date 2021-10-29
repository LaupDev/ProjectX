package com.laupdev.projectx.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): User
}