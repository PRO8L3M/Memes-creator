package com.pro8l3m.memescreator.presentation.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.pro8l3m.domain.models.MemeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalFoundationApi
@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navController: NavController) {
    val state = viewModel.viewState
    DashboardPage(
        state.value,
        viewModel.effect,
        viewModel::onFetchButtonClicked,
        viewModel::onMemeClicked
    ) {
        navController.currentBackStackEntry?.arguments?.putSerializable("meme", it.meme)
        navController.navigate(it.route)
    }
}

@ExperimentalFoundationApi
@Composable
fun DashboardPage(
    state: DashboardContract.State,
    effectFlow: Flow<DashboardContract.Effect>?,
    onButtonClicked: () -> Unit,
    onMemeClicked: (MemeModel) -> Unit,
    onNavigationRequest: (DashboardContract.Effect.Navigation.NavigateToMemeOverview) -> Unit
) {
    LaunchedEffect("CONSTANT") {
        effectFlow?.onEach {
            when (it) {
                is DashboardContract.Effect.FetchedMemes -> { }
                is DashboardContract.Effect.Navigation.NavigateToMemeOverview -> {
                    onNavigationRequest(it)
                }
            }
        }?.collect()
    }
    Scaffold(
        drawerContent = {
            Text(text = "Option one")
            Text(text = "Option two")
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Dashboard",
                fontSize = 30.sp
            )
            Button(onClick = { onButtonClicked() }) {
                Text(text = "Fetch memes")
            }
            MemesList(
                memes = state.memes,
                onMemeClicked = onMemeClicked
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun MemesList(
    memes: List<MemeModel> = listOf(),
    onMemeClicked: (MemeModel) -> Unit = {}
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
    ) {
        items(memes) { meme ->
            Meme(
                meme = meme,
                onMemeClicked = onMemeClicked
            )
        }
    }
}

@Preview
@Composable
fun Meme(
    meme: MemeModel = MemeModel(
        id = 0L,
        name = "",
        tags = emptyList(),
        imageUrl = ""
    ),
    onMemeClicked: (MemeModel) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onMemeClicked(meme) },
        backgroundColor = Color.Gray
    ) {
        Image(
            painter = rememberImagePainter(meme.imageUrl),
            contentDescription = meme.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

}
