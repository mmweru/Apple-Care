package com.example.applecare.UINeeds

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.applecare.R
import com.example.applecare.presentation.sign_in.SignInState
import com.example.applecare.presentation.sign_in.SignInViewModel
import com.google.relay.compose.ColumnScopeInstanceImpl.align

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
    ){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    val backgroundColor = Color(0xFF0D6446)
    Column(modifier = Modifier
        .background(backgroundColor)
        .fillMaxHeight(1f)
        .fillMaxWidth()
    ){
        Box(modifier = Modifier
            .background(backgroundColor)
            .fillMaxHeight(0.6f)){
            WelcomePage()
            Flipped()
            Logo()
        }
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(30.dp))
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Box{
                Button(
                    onClick = { onSignInClick()},
                    modifier = Modifier
                        .height(75.dp)
                        .width(350.dp)
                        .align(Alignment.Center)
                        .offset(y = 150.dp, x = 13.dp),

                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF0D6446), // Set the background color to green
                        contentColor = Color.White // Set the text color to white
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Color.White)
                    Text(text = "Sign in", fontSize = 20.sp, color = Color.White)
                }
            }

        }
    }
}


@Preview
@Composable
fun WelcomePage(){
    Row (
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background content (if any)

        // Image placed at the top right corner
        Image(
            painter = painterResource(id = R.drawable.fruits_2_myfruits_2),
            contentDescription = "Apples Image", // Provide a meaningful description
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .size(340.dp, 210.dp) // Adjust the size as needed
                .offset(x = 120.dp, y = 0.dp) // Adjust the offset as needed
        )
    }
}
@Preview
@Composable
fun Flipped() {
    Row (
        modifier = Modifier
            .fillMaxSize()
    ) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setToSaturation(1f)
        val colorFilter = ColorFilter.tint(Color.White, BlendMode.Hardlight)
        Image(
            painter = painterResource(id = R.drawable.fruits_2_myfruits_2),
            contentDescription = "Apples Image", // Provide a meaningful description
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.BottomCenter,
            modifier = Modifier
                .size(320.dp, 340.dp) // Adjust the size as needed
                .offset(x = 0.dp, y = 210.dp) // Adjust the offset as needed
                .graphicsLayer {
                    scaleX = -1f // Flip horizontally by scaling the X-axis
                    scaleY = -1f // Flip vertically by scaling the Y-axis

                }
        )
    }
}
@Preview
@Composable
fun Logo() {
    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.apples_1_myapples_1),
            contentDescription = "Apples Logo", // Provide a meaningful description
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier.run {
                size(80.dp, 93.dp) // Adjust the size as needed
                    .offset(x = 30.dp, y = 91.dp) // Adjust the offset as needed
            }

        )
        val myfont = FontFamily(Font(R.font.relay_jacques_francois_regular))
        Text(text = "Welcome", color = Color.White, fontFamily = myfont, fontSize = 28.sp, modifier = Modifier.offset(x=25.dp, y = 168.dp), textAlign = TextAlign.Start)
        Text(text = "Sign in to continue", color = Color.White, fontFamily = myfont, fontSize = 25.sp, modifier = Modifier.offset(x=25.dp, y = 194.dp), textAlign = TextAlign.Start)
    }

}
@Preview
@Composable
fun LogInfo() {
    Box {
        Text(
            text = "Email ",
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.relay_jacques_francois_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF0D6446),
                letterSpacing = 1.74.sp,
            ),
            modifier = Modifier
                .padding(vertical=6.dp, horizontal = 10.dp)
        )
        var email by remember { mutableStateOf("") }
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                //.fillMaxSize(0.8f)
                .fillMaxHeight(0.245f)
                .fillMaxWidth(0.98f)
                .padding(vertical = 30.dp, horizontal = 20.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFF0D6446),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            visualTransformation = if (email.isEmpty()) VisualTransformation.None else VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "Password ",
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.relay_jacques_francois_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF0D6446),
                letterSpacing = 1.70.sp,
            ),
            modifier = Modifier
                .padding(vertical = 110.dp, horizontal = 10.dp)
        )
        var password by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .width(345.dp)
                .height(55.dp)
                .offset(y = 139.dp, x = 18.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFF0D6446),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true
        )
        LogInButton()

        }
}

@Preview
@Composable
fun LogInButton(){
    Row(modifier = Modifier.padding(vertical = 55.dp, horizontal = 20.dp)){

    }
    Row(modifier = Modifier.offset(y = 390.dp, x = 28.dp)){
        Text(
            text = "Sign up",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.relay_jacques_francois_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF0D6446),

                letterSpacing = 1.7.sp,
            )
        )
        Spacer(modifier = Modifier.width(110.dp))
        Text(
            text = "Forgot Password",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.relay_jacques_francois_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF0D6446),
                letterSpacing = 1.7.sp,
            )
        )
    }



}
