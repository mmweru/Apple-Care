package com.example.applecare.UINeeds

import android.window.SplashScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.applecare.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec= tween(
            durationMillis = 3500
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4500)
//        navController.popBackStack()
        navController.navigate("sign-in")
    }
    SplashScreen(alpha = alphaAnim.value)
}

@Composable
fun SplashScreen(alpha: Float){
    val backgroundColor = Color(0xFF0D6446)
    Column(modifier = Modifier
        .background(backgroundColor)
        .fillMaxSize()
    ){
      Box{
          TopRightImage()
          FlippedImage()
          LogoImage()
      }
    }
}

@Preview
@Composable
fun TopRightImage() {
    Row (
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background content (if any)

        // Image placed at the top right corner
        Image(
            painter = painterResource(id = R.drawable.fruits_2_myfruits_2),
            contentDescription = "Apples Image", // Provide a meaningful description
            contentScale = ContentScale.Crop,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .size(360.dp, 225.dp) // Adjust the size as needed
                .offset(x = 80.dp, y = 0.dp) // Adjust the offset as needed
                .alpha(1f)
        )
    }
}
@Preview
@Composable
fun FlippedImage() {
        Row (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.fruits_2_myfruits_2),
                contentDescription = "Apples Image", // Provide a meaningful description
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.BottomCenter,
                modifier = Modifier
                    .size(325.dp, 360.dp) // Adjust the size as needed
                    .offset(x = 0.dp, y = 550.dp) // Adjust the offset as needed
                    .graphicsLayer {
                        scaleX = -1f // Flip horizontally by scaling the X-axis
                        scaleY = -1f // Flip vertically by scaling the Y-axis
                    }
            )
        }
}
@Preview
@Composable
fun LogoImage() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.apples))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
        )
    LaunchedEffect(key1 = progress) {
        if (progress == 0f){
            isPlaying = true
        }
        if (progress == 1f){
            isPlaying = false
        }
    }
    //val imageResource: Painter =  painterResource(id = R.drawable.apples_1_myapples_1)
    Box (
        modifier = Modifier
            .fillMaxSize()
            //.background(color = Color(0xFF0D6446))
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(340.dp)
                .offset(y = 133.dp),
            composition = composition,
            alignment = Alignment.Center,
            progress = { progress }
        )
//        Button(onClick = { isPlaying = true}) {
//            Text(text = "Play Again")
//        }
//        Image(
//            painter = imageResource,
//            contentDescription = "Apples Logo", // Provide a meaningful description
//            contentScale = ContentScale.FillBounds,
//            alignment = Alignment.Center,
//            modifier = Modifier.run {
//                size(196.dp, 167.dp) // Adjust the size as needed
//                        .offset(x = 105.dp, y = 280.dp) // Adjust the offset as needed
//            }
//
//        )
        val myfont = FontFamily(Font(R.font.relay_jacques_francois_regular))
        Text(text = "AppleCare AI", color = Color.White,fontFamily = myfont, fontSize = 40.sp, fontWeight = FontWeight(400), modifier = Modifier.offset(x=70.dp, y = 432.dp) .clickable { isPlaying = true }, textAlign = TextAlign.Start)

    }

}

















