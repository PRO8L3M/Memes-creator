package com.pro8l3m.memescreator.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pro8l3m.domain.models.MemeModel
import com.pro8l3m.memescreator.presentation.dashboard.DashboardScreen
import com.pro8l3m.memescreator.presentation.dashboard.DashboardViewModel
import com.pro8l3m.memescreator.presentation.meme.MemeScreen
import com.pro8l3m.memescreator.presentation.splash.SplashScreen
import com.pro8l3m.memescreator.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            ComposeTemplateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") { SplashScreen(navController) }
                        composable("dashboard") { DashboardScreen(dashboardViewModel, navController) }
                        composable("meme_overview") {
                            val meme = navController.previousBackStackEntry?.arguments?.getSerializable("meme") as? MemeModel
                            meme?.let {
                                MemeScreen(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun SplashScreen(viewModel: MainViewModel) {
    val themeMode: Int by viewModel.themeMode.collectAsState()
    SplashPage(
        themeMode,
        { viewModel.setThemeMode(MainViewModel.LIGHT_MODE) },
        { viewModel.setThemeMode(MainViewModel.DARK_MODE) }
    )
}

@Preview
@Composable
fun SplashPagePreview() {
    com.pro8l3m.memescreator.presentation.splash.SplashPage()
}

@Composable
fun SplashPage(
    themeMode: Int = -1,
    onLightThemeClick: () -> Unit = {},
    onDarkThemeClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(
            fontSize = 20.sp,
            text = buildAnnotatedString {
                append("Theme mode: ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                ) {
                    append(themeMode.toString())
                }
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        ThemeButton(text = "LIGHT THEME", onLightThemeClick)
        Spacer(modifier = Modifier.height(8.dp))
        ThemeButton(text = "DARK THEME", onDarkThemeClick)
    }
}

@Preview
@Composable
fun ThemeButtonPreview() {
    ThemeButton(text = "Test button")
}


@Composable
fun ThemeButton(text: String, onClick: () -> Unit = {}) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}*/
