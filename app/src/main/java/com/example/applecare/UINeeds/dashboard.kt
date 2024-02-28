package com.example.applecare.UINeeds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.applecare.R

@Preview
@Composable
fun DashBoard(){

    Box (modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bgimage), // Replace R.drawable.background_image with your image resource
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Adjust the content scale as needed
            modifier = Modifier.fillMaxSize()
        )
        Box (modifier = Modifier
            .width(362.dp)
            .height(710.dp)
            .align(Alignment.Center)
            .background(color = Color(0x82000000), shape = RoundedCornerShape(size = 30.dp))
        ){
            Row (modifier = Modifier.width(311.dp) .height(57.dp) .align(Alignment.TopCenter) .padding(horizontal = 32.dp, vertical = 6.dp)){
                Text(
                    text = "AppleCare Ai",
                    style = TextStyle(
                        fontSize = 40.sp,
                        lineHeight = 55.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )

            }

        }

    }

}