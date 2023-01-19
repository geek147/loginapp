package com.codeonwheels.loginapp.data.repository

import com.codeonwheels.loginapp.domain.model.LoginBody
import com.codeonwheels.loginapp.domain.model.LoginModel
import com.codeonwheels.loginapp.domain.model.UserModel

interface SaltRepositoryInterface {
    suspend fun userLogin(username: String, password : String) : LoginModel
    suspend fun getAllUser(): UserModel
    suspend fun getDetailUser(userId: Int): UserModel.UserModelItem
    suspend fun addUser(body: UserModel.UserModelItem) : UserModel.UserModelItem
    suspend fun updateUser(userId: Int, body: UserModel.UserModelItem) : UserModel.UserModelItem
    suspend fun deleteUser(userId: Int): UserModel.UserModelItem
}