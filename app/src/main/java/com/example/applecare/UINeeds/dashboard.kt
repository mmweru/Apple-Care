package com.example.applecare.UINeeds

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.applecare.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashBoard(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bgimage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .width(362.dp)
                .height(710.dp)
                .align(Alignment.Center)
                .background(color = Color(0xAB000000), shape = RoundedCornerShape(size = 30.dp))
        ) {
            val myfont = FontFamily(
                Font(R.font.irish_grover, FontWeight.Black)
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.67f)
                .offset(y = 20.dp)) {
                Row(
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Adjust padding as needed
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "AppleCare Ai",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontFamily = myfont,
                            lineHeight = 55.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(0.9f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HorizontalPager(state = pagerState) { page ->
                        when (page) {
                            0 -> InteractiveNeonRectangleWithImage(onClick = { navController.navigate("camera") })
                            1 -> InteractiveNeonRectangleWithImage1()
                            2 -> InteractiveNeonRectangleWithImage2()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { index ->
                        val color = if (index == pagerState.currentPage) Color.White else Color.Gray
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(color = color, shape = CircleShape)
                        )
                    }
                }

            }
            Column (modifier = Modifier.fillMaxHeight(0.20f) .offset(y=480.dp)){
                AnimatedTextWithBox()
            }
        }
    }
}




//@Composable
//fun NeonStrokeRectangle() {
//    Canvas(modifier = Modifier.fillMaxSize()) {
//        var strokeWidth = 4.dp.toPx()
//        val halfStrokeWidth = strokeWidth / 2
//        val strokePaint = Paint().apply {
//            style = PaintingStyle.Stroke
//            strokeWidth = strokeWidth
//            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
//        }
//
//        // Draw outer rectangle with neon stroke
//        drawRoundRect(
//            color = Color.Transparent,
//            topLeft = Offset(50f, 50f),
//            size = Size(300f, 200f),
//            cornerRadius = 16.dp.toPx(),
//            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
//            paint = strokePaint
//        )
//
//        // Draw inner rectangle with solid fill
//        drawRoundRect(
//            color = Color.Transparent,
//            topLeft = Offset(50f + halfStrokeWidth, 50f + halfStrokeWidth),
//            size = Size(300f - strokeWidth, 200f - strokeWidth),
//            cornerRadius = 16.dp.toPx() - halfStrokeWidth,
//            style = PaintingStyle.Fill,
//            brush = Brush.linearGradient(
//                colors = listOf(Color.Yellow, Color.Green, Color.White),
//                start = Offset(50f, 50f),
//                end = Offset(350f, 250f)
//            )
//        )
//    }
//}

@Preview
@Composable
fun InteractiveNeonRectangleWithImage(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.myinage1, // Replace with your image resource
    onClick: () -> Unit = {},
    text: String = "Camera"
) {
    val strokeColors = listOf(Color.Yellow, Color.Green, Color.White)
    var isTapped by remember { mutableStateOf(false) }
    val myfont = FontFamily(
        Font(R.font.irish_grover, FontWeight.Black)
    )

    Box(
        modifier = modifier.clickable(onClick = { onClick(); isTapped = !isTapped })
    ) {
        Canvas(modifier = Modifier
            .offset(y = 20.dp)
            .width(347.dp) // Adjust as needed
            .height(562.dp) // Adjust as needed
            .padding(5.dp)
            .background(color = Color.White)) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color.Green, Color.Yellow, Color.White) else strokeColors
                ),
                style = Stroke(width = 19f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
//                .fillMaxSize() // Fills the available space within the Box
                .align(Alignment.Center) // Centers the image
                .shadow( // Apply the shadow effect
                    elevation = 0.dp, // Adjust as needed (was 12.1999...)
                    spotColor = Color(0x80000000),
                    ambientColor = Color(0x66000000)
                )

                .padding(0.dp) // No padding needed
                .width(398.dp) // Adjust as needed
                .height(278.dp) // Adjust as needed
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp, // Adjust font size as needed
                fontFamily = myfont,
                color = Color.Black, // Adjust text color as needed
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align text at bottom center
                .padding(bottom = 10.dp) // Add padding from bottom
        )
    }
}
@Preview
@Composable
fun InteractiveNeonRectangleWithImage1(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.apple, // Replace with your image resource
    onClick: () -> Unit = {},
    text: String = "History"
) {
    val strokeColors = listOf(Color.Yellow, Color.Green, Color.White)
    var isTapped by remember { mutableStateOf(false) }
    val myfont = FontFamily(
        Font(R.font.irish_grover, FontWeight.Black)
    )

    Box(
        modifier = modifier.clickable(onClick = { onClick(); isTapped = !isTapped })
    ) {
        Canvas(modifier = Modifier
            .offset(y = 20.dp)
            .width(347.dp) // Adjust as needed
            .height(562.dp) // Adjust as needed
            .padding(5.dp)
            .background(color = Color.White)) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 29f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
//                .fillMaxSize() // Fills the available space within the Box
                .align(Alignment.Center) // Centers the image
                .shadow( // Apply the shadow effect
                    elevation = 0.dp, // Adjust as needed (was 12.1999...)
                    spotColor = Color(0x80000000),
                    ambientColor = Color(0x66000000)
                )

                .padding(0.dp) // No padding needed
                .width(438.dp) // Adjust as needed
                .height(278.dp) // Adjust as needed
                .fillMaxSize()
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp, // Adjust font size as needed
                fontFamily = myfont,
                color = Color.Black, // Adjust text color as needed
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align text at bottom center
                .padding(bottom = 10.dp) // Add padding from bottom
        )
    }
}

@Preview
@Composable
fun InteractiveNeonRectangleWithImage2(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.home, // Replace with your image resource
    onClick: () -> Unit = {},
    text: String = "Home"
) {
    val strokeColors = listOf(Color.Yellow, Color.Green, Color.White)
    var isTapped by remember { mutableStateOf(false) }
    val myfont = FontFamily(
        Font(R.font.irish_grover, FontWeight.Black)
    )

    Box(
        modifier = modifier.clickable(onClick = { onClick(); isTapped = !isTapped })
    ) {
        Canvas(modifier = Modifier
            .offset(y = 20.dp)
            .width(347.dp) // Adjust as needed
            .height(562.dp) // Adjust as needed
            .padding(5.dp)
            .background(color = Color.White)) {

            drawRoundRect(
                brush = Brush.linearGradient(
                    if (isTapped) listOf(Color(0xFF027A12), Color(0xB007FFE5), Color.White) else strokeColors
                ),
                style = Stroke(width = 19f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Image",
            modifier = Modifier
//                .fillMaxSize() // Fills the available space within the Box
                .align(Alignment.CenterStart) // Centers the image
                .shadow( // Apply the shadow effect
                    elevation = 0.dp, // Adjust as needed (was 12.1999...)
                    spotColor = Color(0xE6000000),
                    ambientColor = Color(0xF8000000)
                )

                .padding(0.dp) // No padding needed
                .width(320.dp) // Adjust as needed
                .height(298.dp) // Adjust as needed
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp, // Adjust font size as needed
                fontFamily = myfont,
                color = Color.Black, // Adjust text color as needed
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align text at bottom center
                .padding(bottom = 10.dp)// Add padding from bottom
        )
    }
}

@Preview
@Composable
fun AnimatedTextWithBox() {
    val text = "Apples 🍏 don't just grow on trees 🌳;\nthey grow on hard work ⚒️ ,\ndedication, and a love ❤️ for the land."
    val cursor = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(159.dp)
            .padding(10.dp)
            .background(Color(0x800E0D0D), shape = RoundedCornerShape(16.dp))
            .border(
                width = 2.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Yellow, Color.Green, Color.Black)
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        val animatedText = rememberInfiniteTransition()
        val textState by animatedText.animateValue(
            initialValue = 0,
            targetValue = text.length,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 10000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        val typedText = remember { mutableStateOf("") }

        LaunchedEffect(key1 = textState) {
            typedText.value = text.take(textState)
        }

        Text(
            text = typedText.value + if (cursor.value) "|" else "",
            style = TextStyle(fontSize = 16.sp),
            maxLines = 4,
            color = Color.White
        )

        LaunchedEffect(key1 = textState) {
            delay(800)
            cursor.value = !cursor.value
        }
    }
}



