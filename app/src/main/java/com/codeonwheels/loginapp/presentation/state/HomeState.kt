package com.codeonwheels.loginapp.presentation.state

import com.codeonwheels.loginapp.domain.model.UserModel

data class HomeState(
    var isLoading: Boolean = false,
    var success: Int = -1,
    var error: String = "",
    var internet: Boolean = false,
    var currentUser: UserModel.UserModelItem? = null,
    var userList: UserModel? = null
)