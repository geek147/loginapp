package com.codeonwheels.loginapp.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LoginModel(
    @SerializedName("token")
    val token: String?
)