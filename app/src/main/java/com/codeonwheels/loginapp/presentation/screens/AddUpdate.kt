package com.codeonwheels.loginapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.codeonwheels.loginapp.Routes
import com.codeonwheels.loginapp.domain.model.UserModel
import com.codeonwheels.loginapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun AddUpdate(
    navController: NavHostController,
) {
    Box(modifier = Modifier.fillMaxSize()){
        AddUpdatePage(navController)
    }
}

@Composable
fun AddUpdatePage(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    when (state.success) {
        0 -> {

        }
        1 -> {

        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                }
            )
        }) {
        Box(modifier = Modifier.background(Color.White)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val email = remember { mutableStateOf(TextFieldValue()) }
                val phone = remember { mutableStateOf(TextFieldValue()) }

                Text(text = "Add or Update", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Monospace))

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })

                TextField(
                    label = { Text(text = "name") },
                    value = phone.value,
                    onValueChange = { phone.value = it })

                TextField(
                    label = { Text(text = "email") },
                    value = email.value,
                    onValueChange = { email.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            viewModel.addUser(
                                UserModel.UserModelItem(
                                   address = null,
                                   email = email.value.text,
                                    phone = phone.value.text,
                                    username = username.value.text,
                                    password = password.value.text,
                                    id = null,
                                    name = null
                                )
                            )
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}
