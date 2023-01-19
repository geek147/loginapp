package com.codeonwheels.loginapp.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeonwheels.loginapp.domain.usecase.LoginUseCase
import com.codeonwheels.loginapp.presentation.state.LoginState
import com.codeonwheels.loginapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    var job: Job? = null

    fun getUserLogin(email: String, password: String) {

        job = viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(
                username = email,
                password = password
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = LoginState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data != null && result.data.token.isNullOrEmpty()) {
                            _state.value = LoginState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                token = result.data?.token.orEmpty(),
                                success = 1
                            )
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = LoginState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun clearViewModel() {
        state.value.internet = false
        state.value.isLoading = false
        state.value.success = -1
        state.value.error = ""
    }
}

