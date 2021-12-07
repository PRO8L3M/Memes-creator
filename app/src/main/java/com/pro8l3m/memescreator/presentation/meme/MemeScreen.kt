package com.pro8l3m.memescreator.presentation.meme

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.pro8l3m.domain.models.MemeModel
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@Composable
fun MemeScreen(meme: MemeModel) {

    //todo Most of them should be moved to VM
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val text = remember { mutableStateOf("Your caption") }
    val fontSize = remember { mutableStateOf(15.sp) }
    val doubleClicked = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = meme.name)
        Image(
            painter = rememberImagePainter(meme.imageUrl),
            contentDescription = meme.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )
    }

    Box(modifier = Modifier
        .size(200.dp)
        .graphicsLayer(
            scaleX = scale,
            scaleY = scale,
            rotationZ = rotation,
            translationX = offset.x,
            translationY = offset.y
        )
        .transformable(state = state)
        .background(Color.LightGray)
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    Log.i("Compose", "TextField tap")
                    val randomFontSize = IntRange(15, 30).random().sp
                    fontSize.value = randomFontSize
                    doubleClicked.value = !doubleClicked.value
                }
            )
        }
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consumeAllChanges()
                offset += dragAmount
            }
        }
    ) {
        if (doubleClicked.value) CaptionEditView(offsetX.roundToInt(), offsetY.roundToInt())

        BasicTextField(
            value = text.value,
            enabled = false,
            onValueChange = { text.value = it },
            textStyle = TextStyle(fontSize = fontSize.value),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .focusRequester(focusRequester)
                .height(60.dp)
        )
    }
}

@Composable
fun CaptionEditView(offsetX: Int, offsetY: Int) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color.LightGray
            )
    ) {

    }
}