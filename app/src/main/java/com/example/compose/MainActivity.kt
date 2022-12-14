package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GameGround()
                }
            }
        }
    }
}

@Composable
fun GameGround() {
    var progress by remember { mutableStateOf(0.5f) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(R.drawable.bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.wrapContentSize()
        )

        Image(
            painterResource(R.drawable.base),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Level",
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )

                Slider(
                    value = progress,
                    onValueChange = { progress = it },
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = "Pop",
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.pop_bg),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(70.dp)
                    )
                    Image(
                        painterResource(R.drawable.pop),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            var list by remember {
                mutableStateOf(listOf<String>())
            }
            for (i in list) {
                key(i) {
                    ballRow()
                }
            }
            LaunchedEffect(Unit) {
                for (i in 0..1000) {
                    delay(if (progress == 0f) 100 else (progress * 2000).toLong())
                    val lisst = list.toMutableList()
                    lisst.add(i.toString())
                    if (lisst.size > 10) {
                        lisst.removeAt(0)
                    }
                    list = lisst
                }
            }
        }
    }
}

@Composable
fun ballRow() {
    var start by remember { mutableStateOf(true) }
    val duration = 10000
    val configuration = LocalConfiguration.current
    val size by animateDpAsState(
        if (start) 0.dp else 200.dp,
        tween(duration)
    )
    val yOffset by animateDpAsState(
        if (start) 0.dp else configuration.screenHeightDp.dp * 1.2f,
        tween(duration)
    )
    val xOffset1 by animateDpAsState(
        if (start) 0.dp else -configuration.screenHeightDp.dp / 3,
        tween(duration)
    )
    val xOffset2 by animateDpAsState(if (start) 0.dp else 0.dp)
    val xOffset3 by animateDpAsState(
        if (start) 0.dp else configuration.screenHeightDp.dp / 3,
        tween(duration)
    )
// https://api.mocklets.com/p68543/hook
    LaunchedEffect(Unit) {
        start = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 315.dp),
        contentAlignment = Alignment.TopCenter

    ) {
        Row {
            val list = remember {
                mutableListOf(
                    R.drawable.ball,
                    R.drawable.square,
                    R.drawable.triangle
                ).shuffled()
            }

            Box(
                modifier = Modifier.wrapContentSize().size(size)
                    .offset(x = xOffset1, y = yOffset),
                contentAlignment = Alignment.Center
            ) {
                var explode by remember { mutableStateOf(false) }
                val scale by animateFloatAsState(
                    targetValue = if (explode.not()) 0f else 1f,
                    tween(100)
                )

                Image(
                    painterResource(list[0]),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .clickable {
                            if (list[0] == R.drawable.square) {
                                explode = true
                            }
                        }
                )
                Image(
                    painterResource(R.drawable.explode),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .scale(scale)
                )
            }
            Box(
                modifier = Modifier.wrapContentSize().size(size)
                    .offset(x = xOffset2, y = yOffset),
                contentAlignment = Alignment.Center
            ) {
                var explode by remember { mutableStateOf(false) }
                val scale by animateFloatAsState(
                    targetValue = if (explode.not()) 0f else 1f,
                    tween(100)
                )
                Image(
                    painterResource(list[1]),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .clickable {
                            if (list[1] == R.drawable.square) {
                                explode = true
                            }
                        }
                )
                Image(
                    painterResource(R.drawable.explode),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .scale(scale)
                )
            }
            Box(
                modifier = Modifier.wrapContentSize().size(size)
                    .offset(x = xOffset3, y = yOffset),
                contentAlignment = Alignment.Center
            ) {
                var explode by remember { mutableStateOf(false) }
                val scale by animateFloatAsState(
                    targetValue = if (explode.not()) 0f else 1f,
                    tween(100)
                )
                Image(
                    painterResource(list[2]),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .clickable {
                            if (list[2] == R.drawable.square) {
                                explode = true
                            }
                        }
                )
                Image(
                    painterResource(R.drawable.explode),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize()
                        .scale(scale)
                )
            }
        }
//            }
    }
}

@Composable
fun LottieAnimation() {
    var isLottiePlaying by remember {
        mutableStateOf(true)
    }
    var animationSpeed by remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.blast)
    )

    // to control the lottie animation
    val lottieAnimation by animateLottieCompositionAsState(
        // pass the composition created above
        composition,
        // Iterates Forever
        iterations = LottieConstants.IterateForever,
        // Lottie and pause/play
        isPlaying = isLottiePlaying,
        // Increasing the speed of change Lottie
        speed = animationSpeed,
        restartOnPlay = false

    )
}

@Preview
@Composable
fun preview() {
    GameGround()
}
