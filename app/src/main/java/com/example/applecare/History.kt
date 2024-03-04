//package com.example.applecare
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.semantics.Role.Companion.Image
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//
//@Composable
//fun History(navController: NavHostController) {
//    val viewModel = viewModel<MainViewModel>()
//    val predictions = viewModel.predictions.collectAsState().value
//
//    if (predictions.isEmpty()) {
//        Text(text = "No saved predictions yet.")
//    } else {
//        LazyColumn {
//            this.items(predictions) { prediction ->
//                PredictionItem(prediction = prediction)
//            }
//        }
//    }
//}
//
//@Composable
//fun PredictionItem(prediction: MainViewModel.Prediction) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            val imageBitmap: ImageBitmap = prediction.bitmap.asImageBitmap()
//
//// Then use the ImageBitmap in the Image composable
//            Image(
//                bitmap = imageBitmap,
//                contentDescription = "Captured image",
//                modifier = Modifier.size(80.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(text = "Prediction:")
//                // Access and display individual prediction results from prediction.results
//                // You need to modify this part based on your prediction format
//                prediction.results.forEachIndexed { index, value ->
//                    Text(text = "  - Class ${index + 1}: ${value.toString()}%")
//                }
//            }
//        }
//    }
//}
