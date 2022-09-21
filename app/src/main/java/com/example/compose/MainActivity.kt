package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

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
//                    GameGround(0.5f)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameGround(offset: Float) {
    var visible by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(R.drawable.bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painterResource(R.drawable.base),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = 60.dp)
                .clickable {
                    visible = !visible
                },
            contentAlignment = Alignment.TopCenter
        ) {
            Column {
                Text(
                    text = "Pop",
                    color = Color.White
                )
                Box(
                    modifier = Modifier.wrapContentHeight()
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.pop_bg),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painterResource(R.drawable.pop),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        val configuration = LocalConfiguration.current
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Row {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = slideOut(
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    ) {
                        IntOffset(configuration.screenWidthDp * -1, configuration.screenHeightDp)
                    }
                ) {
                    Image(
                        painterResource(R.drawable.ball),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = slideOut(
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    ) {
                        IntOffset(0, configuration.screenHeightDp)
                    }
                ) {
                    Image(
                        painterResource(R.drawable.square),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = slideOut(
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    ) {
                        IntOffset(configuration.screenWidthDp, configuration.screenHeightDp)
                    }
                ) {
                    Image(
                        painterResource(R.drawable.triangle),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    GameGround(0.5f)
}
