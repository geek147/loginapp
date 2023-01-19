package com.codeonwheels.loginapp.presentation.state

data class LoginState(
    var isLoading : Boolean = false,
    var success : Int = -1,
    var token: String = "",
    var error : String = "",
    var internet : Boolean = false
)