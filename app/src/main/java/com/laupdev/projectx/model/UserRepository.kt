package com.laupdev.projectx.model

import com.laupdev.projectx.database.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun getUserByUsername(username: String) = userDao.getUserByUsername(username)

    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

}