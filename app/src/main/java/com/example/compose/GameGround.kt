package com.example.compose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun GameGround2(
    ballAnimation: Float
) {
    val context = LocalContext.current
    Box {
        val resources = LocalContext.current.resources
        val ballAnim = rememberInfiniteTransition()
        /*var canvasSize by remember { mutableStateOf<IntSize>(IntSize()) }
        val anim by ballAnim.animateFloat(
            initialValue = canvasSize,
            targetValue = 1f,
            animationSpec = InfiniteRepeatableSpec(
                animation = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        )*/

        val render1Ball = remember {
            R.drawable.ic_launcher_foreground
        }
        val render2Ball = remember {
            R.drawable.ic_launcher_foreground
        }
        val render3Ball = remember {
            R.drawable.ic_launcher_foreground
        }
        val render4Ball = remember {
            R.drawable.ic_launcher_foreground
        }

        Canvas(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp)
                .fillMaxSize(),
            onDraw = {
                val w = size.width
                val h = size.height

                val s1LineOffset = w / 4 - 10
                val s2LineOffset = w * 3 / 8 - 10
                val sMLineOffset = w / 2
                val s3LineOffset = w * 5 / 8 + 10
                val s4LineOffset = w * 3 / 4 + 10

                val b1StartOffset = (s2LineOffset - s1LineOffset) / 2 + s1LineOffset - 50
                val b2StartOffset = (sMLineOffset - s2LineOffset) / 2 + s2LineOffset - 50
                val b3StartOffset = (s3LineOffset - sMLineOffset) / 2 + sMLineOffset - 50
                val b4StartOffset = (s4LineOffset - s3LineOffset) / 2 + s3LineOffset - 50

                val e1LineOffset = 0f
                val e2LineOffset = w / 4 - 5
                val eMLineOffset = w / 2
                val e3LineOffset = w * 3 / 4 + 5
                val e4LineOffset = w

                val eb1StartOffset = (e2LineOffset - e1LineOffset) / 2 + e1LineOffset - 50
                val eb2StartOffset = (eMLineOffset - e2LineOffset) / 2 + e2LineOffset - 50
                val eb3StartOffset = (e3LineOffset - eMLineOffset) / 2 + eMLineOffset - 50
                val eb4StartOffset = (e4LineOffset - e3LineOffset) / 2 + e3LineOffset - 50

                // Background for game ground
                val path = Path().apply {
                    moveTo(s1LineOffset, 0f)
                    lineTo(s4LineOffset, 0f)
                    lineTo(w, h)
                    lineTo(e1LineOffset, h)
                }
                drawPath(path = path, color = Color.Red)

                // First Ball Start Position
                val option = BitmapFactory.Options()
                option.inPreferredConfig = Bitmap.Config.ARGB_8888
                val bitmap = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ball,
                    option
                ).asImageBitmap()
                drawImage(
                    image = bitmap,
                    topLeft = Offset(
                        x = b1StartOffset,
                        y = 0f
                    )
                )

                // Second Ball Start Position
                drawImage(
                    image = bitmap,
                    topLeft = Offset(
                        x = b2StartOffset,
                        y = 0f
                    )
                )

                // Third Ball Start Position
                drawImage(
                    image = bitmap,
                    topLeft = Offset(
                        x = b3StartOffset,
                        y = 0f
                    )
                )

                // fourth Ball Start Position
                drawImage(
                    image = bitmap,
                    topLeft = Offset(
                        x = b4StartOffset,
                        y = 0f
                    )
                )

                // First Line
                drawLine(
                    color = Color.Blue,
                    start = Offset(w / 4 - 10, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 5f,
                    cap = StrokeCap.Butt
                )

                // Second Line
                drawLine(
                    color = Color.Green,
                    start = Offset(w * 3 / 8 - 10, 0f),
                    end = Offset(w / 4 - 5, size.height),
                    strokeWidth = 5f,
                    cap = StrokeCap.Butt
                )

                // Center Line
                drawLine(
                    color = Color.Magenta,
                    start = Offset(w / 2, 0f),
                    end = Offset(w / 2, size.height),
                    strokeWidth = 5f,
                    cap = StrokeCap.Butt
                )

                // Third Line
                drawLine(
                    color = Color.Yellow,
                    start = Offset(w * 5 / 8 + 10, 0f),
                    end = Offset(w * 3 / 4 + 5, size.height),
                    strokeWidth = 5f,
                    cap = StrokeCap.Butt
                )
                // Fourth Line
                drawLine(
                    color = Color.Cyan,
                    start = Offset(w * 3 / 4 + 10, 0f),
                    end = Offset(w, size.height),
                    strokeWidth = 5.dp.toPx(),
                    cap = StrokeCap.Butt
                )
            }
        )
    }
}
