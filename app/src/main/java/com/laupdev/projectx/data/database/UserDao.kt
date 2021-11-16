package com.laupdev.projectx.data.database

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE is_logged_in = 1")
    suspend fun getLoggedInUser(): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Update(entity = User::class)
    suspend fun updateUserIsLoggedIn(userIsLoggedIn: UserIsLoggedIn)
}