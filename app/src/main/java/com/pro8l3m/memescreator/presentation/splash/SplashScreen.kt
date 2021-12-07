package com.pro8l3m.memescreator.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = viewModel()
    SplashPage(viewModel) {
        navController.popBackStack()
        navController.navigate(it)
    }
}

@Composable
fun SplashPage(viewModel: SplashViewModel, navigationRequest: (String) -> Unit = {}) {
    LaunchedEffect("LAUNCH_LISTEN_FOR_EFFECTS") {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is SplashContract.Effect.Navigation.ToDashboardScreen -> navigationRequest(effect.dashboardScreenRoute)
            }
        }.collect()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Splash screen", fontSize = 30.sp)
    }
}