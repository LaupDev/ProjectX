package com.laupdev.projectx.model

import com.laupdev.projectx.database.UserDao

class UserRepository(private val userDao: UserDao) {

    fun getUserByUsername(username: String) = userDao.getUserByUsername(username)

    fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

}