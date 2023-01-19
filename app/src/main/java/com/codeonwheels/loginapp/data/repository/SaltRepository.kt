package com.codeonwheels.loginapp.data.repository

import com.codeonwheels.loginapp.data.remote.SaltApi
import com.codeonwheels.loginapp.domain.model.LoginBody
import com.codeonwheels.loginapp.domain.model.LoginModel
import com.codeonwheels.loginapp.domain.model.UserModel
import javax.inject.Inject

class SaltRepository @Inject constructor(
    private val api : SaltApi
) : SaltRepositoryInterface {

    override suspend fun userLogin(username: String, password: String): LoginModel {
        val loginBody  = LoginBody(username,password)
        val result = api.login(loginBody)
        return result.body()!!
    }

    override suspend fun getAllUser(): UserModel {
        val result = api.getAllUser()
        return result.body()!!
    }

    override suspend fun getDetailUser(userId: Int): UserModel.UserModelItem {
        val result = api.getDetailUsers(userId)
        return result.body()!!
    }

    override suspend fun addUser(body: UserModel.UserModelItem): UserModel.UserModelItem {
        val result = api.addUser(body)
        return result.body()!!
    }

    override suspend fun updateUser(
        userId: Int,
        body: UserModel.UserModelItem
    ): UserModel.UserModelItem {
        val result = api.updateUser(userId, body)
        return result.body()!!
    }

    override suspend fun deleteUser(userId: Int): UserModel.UserModelItem {
        val result = api.deleteUser(userId)
        return result.body()!!
    }
}