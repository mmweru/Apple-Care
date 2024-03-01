package com.example.applecare

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applecare.UINeeds.AnimatedSplashScreen
import com.example.applecare.UINeeds.DashBoard
import com.example.applecare.UINeeds.MyNavHost
import com.example.applecare.UINeeds.SignInScreen
import com.example.applecare.presentation.profile.ProfileScreen
import com.example.applecare.presentation.sign_in.GoogleAuthUiClient
import com.example.applecare.presentation.sign_in.SignInViewModel
import com.example.applecare.ui.theme.AppleCareTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppleCareTheme {

                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen"){
                    composable("splash_screen"){
//                        navController.popBackStack()
                        AnimatedSplashScreen(navController = navController)

                    }
                    composable("sign_in"){
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit){
                            if(googleAuthUiClient.getSignedInUser() != null){
                                navController.navigate("profile")
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(), onResult = {
                                    result ->
                                if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        LaunchedEffect(key1 = state.isSignInSuccessful){
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful...🥂...🎉...🍏",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate("profile")
                                viewModel.resetState()
                            }
                        }

                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )

                                }
                            },
                            navController = navController
                        )

                    }
                    composable( "profile"){
                        val isProfileShown by remember { mutableStateOf(false) } // Track profile shown state

                        LaunchedEffect(key1 = isProfileShown) { // Trigger after profile shown changes
                            if (isProfileShown) {
                                delay(5000) // Delay for 2 seconds (adjust as needed)
                                navController.navigate("dashboard") // Navigate to dashboard
                            }
                        }
                        ProfileScreen(
                            userData = googleAuthUiClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out...👋🏾",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.popBackStack()
                                }
                            },
                            navController = navController

                        )
                    }
                    composable("dashboard"){
                        DashBoard(navController = navController)

                    }
            }
        }
    }
} }



