package com.codeonwheels.loginapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import com.codeonwheels.loginapp.domain.model.UserModel
import com.codeonwheels.loginapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun Detail(
    navController: NavHostController,
) {
    Box(modifier = Modifier.fillMaxSize()){
        DetailPage(navController)
    }
}

@Composable
fun DetailPage(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.getDetailUser(1)
            }
        }
    }

    when (state.success) {
        0 -> {}
        1 -> {

        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detail")
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
                state.currentUser?.let { it1 -> ItemCard2(it1) }
            }
        }
    }
}

@Composable
fun ItemCard2(item: UserModel.UserModelItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp,
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