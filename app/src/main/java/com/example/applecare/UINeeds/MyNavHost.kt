package com.example.applecare.UINeeds

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.applecare.presentation.sign_in.GoogleAuthUiClient
import com.example.applecare.presentation.sign_in.SignInViewModel

@Composable
fun MyNavHost(navController: NavHostController, starDest: String){

//    NavHost(navController = navController, startDestination = starDest){
//        composable("AnimatedSplashScreen"){
//            AnimatedSplashScreen(navController = navController)
//        }
//        composable("sign_in"){
//            val viewModel = viewModel<SignInViewModel>()
//            val state by viewModel.state.collectAsStateWithLifecycle()
//
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.StartIntentSenderForResult(), onResult = {
//                    result ->
//                    if(result.resultCode == RESULT_OK){
//                        lifecycleScope
//                    }
//                }
//            )
//        }
//        composable("Enroll"){
//            Enroll(navController = navController)
//        }
//        composable("DropOff"){
//            DropOff(navController = navController)
//        }
//        composable("DropOff2"){
//            DropOff2(navController = navController)
//        }
//        composable("BarcodeScanner"){
//            BarcodeScanner(navController = navController)
//        }

}