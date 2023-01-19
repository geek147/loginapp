package com.codeonwheels.loginapp.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class UserModel : ArrayList<UserModel.UserModelItem>(){
    @Keep
    data class UserModelItem(
        @SerializedName("address")
        val address: Address?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: Name?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("username")
        val username: String?
    ) {
        @Keep
        data class Address(
            @SerializedName("city")
            val city: String?,
            @SerializedName("geolocation")
            val geolocation: Geolocation?,
            @SerializedName("number")
            val number: Int?,
            @SerializedName("street")
            val street: String?,
            @SerializedName("zipcode")
            val zipcode: String?
        ) {
            @Keep
            data class Geolocation(
                @SerializedName("lat")
                val lat: String?,
                @SerializedName("long")
                val long: String?
            )
        }
    
        @Keep
        data class Name(
            @SerializedName("firstname")
            val firstname: String?,
            @SerializedName("lastname")
            val lastname: String?
        )
    }
}