package com.codeonwheels.loginapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeonwheels.loginapp.domain.model.UserModel
import com.codeonwheels.loginapp.domain.usecase.*
import com.codeonwheels.loginapp.presentation.state.HomeState
import com.codeonwheels.loginapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getListUserUseCase: GetListUserUseCase,
    private val getDetailUserUseCase: GetDetailUserUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state
    var job: Job? = null

    fun getAllUser() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getListUserUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data == null) {
                            _state.value = HomeState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                success = 1,
                                userList = result.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = HomeState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getDetailUser(userId: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            getDetailUserUseCase(userId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data == null) {
                            _state.value = HomeState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                success = 1,
                                currentUser = result.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = HomeState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun addUser(body:UserModel.UserModelItem) {
        job = viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase(body).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = HomeState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                success = 1,
                            )
                            getAllUser()
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = HomeState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateUser(userId: Int, body:UserModel.UserModelItem) {
        job = viewModelScope.launch(Dispatchers.IO) {
            updateUserUseCase(userId, body).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = HomeState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                success = 1,
                            )
                            getAllUser()
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = HomeState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun deleteUser(userId: Int) {
        job = viewModelScope.launch(Dispatchers.IO) {
            deleteUserUseCase(userId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = HomeState(
                            isLoading = true,
                            internet = false
                        )
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = HomeState(
                                isLoading = false,
                                success = 0,
                                internet = false,
                                error = "Data Error"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isLoading = false,
                                internet = false,
                                success = 1,
                            )
                            getAllUser()
                        }
                    }
                    is Resource.Error -> {
                        delay(200)
                        _state.value = HomeState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}