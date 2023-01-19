package com.codeonwheels.loginapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.codeonwheels.loginapp.Routes
import com.codeonwheels.loginapp.domain.model.UserModel
import com.codeonwheels.loginapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun Home(
    navController: NavHostController,
) {
    Box(modifier = Modifier.fillMaxSize()){
        HomePage(navController)
    }
}

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.getAllUser()
            }
        }
    }

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.AddUpdate.route)
                }
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                state.userList?.let { it1 -> ItemList(items = it1, navController) }
            }
        }
    }
}

@Composable
fun ItemList(items: List<UserModel.UserModelItem>, navController: NavHostController) {
    LazyColumn() {
        items(items) { item ->
            ItemCard(item, navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemCard(item: UserModel.UserModelItem, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp,
        onClick = {
            navController.navigate(Routes.Detail.route)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            item.username?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
                )
            }
            item.email?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
                )
            }
            item.phone?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}


