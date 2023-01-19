package com.codeonwheels.loginapp.data.remote

import com.codeonwheels.loginapp.domain.model.LoginBody
import com.codeonwheels.loginapp.domain.model.LoginModel
import com.codeonwheels.loginapp.domain.model.UserModel
import retrofit2.Response
import retrofit2.http.*

interface SaltApi {
    @POST("auth/login")
    suspend fun login(
        @Body requestBody: LoginBody
    ): Response<LoginModel>

    @GET("users")
    suspend fun getAllUser(
    ): Response<UserModel>


    @GET("users/{userId}")
    suspend fun getDetailUsers(
        @Path("userId") userId: Int
    ): Response<UserModel.UserModelItem>

    @POST("users")
    suspend fun addUser(
        @Body requestBody: UserModel.UserModelItem
    ): Response<UserModel.UserModelItem>

    @PUT("users/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: Int,
        @Body requestBody: UserModel.UserModelItem
    ): Response<UserModel.UserModelItem>

    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Path("userId") userId: Int,
    ): Response<UserModel.UserModelItem>

}