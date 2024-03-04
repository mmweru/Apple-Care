@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.example.applecare.cameraui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.Image
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cameraswitch
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.applecare.MainViewModel
import com.example.applecare.PhotoBottomSheetContent
import com.example.applecare.ml.Apple
import kotlinx.coroutines.launch
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min
import mu.KotlinLogging
import okhttp3.internal.concurrent.TaskRunner.Companion.logger


@Composable
fun Camera(navController: NavHostController){

   val logger = KotlinLogging.logger {}

    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                CameraController.VIDEO_CAPTURE
            )
        }
    }
    val scaffoldState = androidx.compose.material3.rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = viewModel<MainViewModel>()
    val bitmaps by viewModel.bitmaps.collectAsState()



    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            PhotoBottomSheetContent(
                bitmaps = bitmaps,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    ){ padding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ){
           CameraPreview(
               controller = controller,
               modifier = Modifier
                   .fillMaxSize()
           )
            IconButton(
                onClick = {
                    controller.cameraSelector = if(controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    }else CameraSelector.DEFAULT_BACK_CAMERA
                },
                modifier = Modifier
                    .offset(16.dp, 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Cameraswitch,
                    contentDescription ="Switch Camera",
                    tint = Color(0xFF0D5210)
                    )

            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                androidx.compose.material3.IconButton(
                    onClick = {
                     scope.launch {
                         scaffoldState.bottomSheetState.expand()
                     }
                    }) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = "Open Gallery",
                        tint = Color(0xFF0C3A0E)
                    )

                }
                androidx.compose.material3.IconButton(
                    onClick = {
                        takePhoto(
                            controller = controller,
                            context = context,
                            onPhotoTaken = viewModel::onTakePhoto
                        )
                    }) {
                    Icon(
                        imageVector = Icons.Outlined.PhotoCamera,
                        contentDescription = "Take Photo",
                        tint = Color(0xFF0C3A0E)
                    )

                }
            }

        }
    }

}

fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
){
    val cameraSelector =  CameraSelector.Builder().build()

    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val bitmap = imageProxyToBitmap(image)
                classifyImage(bitmap, context)
                image.close()
            }

            private fun classifyImage(bitmap: Bitmap?, context: Context) {
                bitmap?.let {
                    try {
                        val model = Apple.newInstance(context)

                        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 256, 256, 3), DataType.FLOAT32)
                        val byteBuffer = ByteBuffer.allocateDirect(4 * 256 * 256 * 3 * 4) // Allocate a larger buffer size
                        byteBuffer.order(ByteOrder.nativeOrder())


                        // Convert Bitmap to ByteBuffer
                        val intValues = IntArray(bitmap.width * bitmap.height)
                        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
                        var pixel = 0
                        for (i in 0 until 256) {
                            for (j in 0 until 256) {
                                val value = intValues[pixel++]
                                byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 255f))
                                byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 255f))
                                byteBuffer.putFloat((value and 0xFF) * (1f / 255f))
                            }
                        }
                        byteBuffer.flip()
                        inputFeature0.loadBuffer(byteBuffer)

                        // Process the input buffer with the model
                        val outputs = model.process(inputFeature0)
                        val outputFeature0 = outputs.getOutputFeature0AsTensorBuffer()

                        // Extract confidence values
                        val confidences = outputFeature0.floatArray
                        var maxPos = 0
                        var maxConfidence = 0f
                        for (i in confidences.indices) {
                            if (confidences[i] > maxConfidence) {
                                maxConfidence = confidences[i]
                                maxPos = i
                            }
                        }

                        // Define classes
                        val classes = arrayOf("Apple___Apple_scab", "Apple___Black_rot", "Apple___Cedar_apple_rust", "Apple___healthy")

                        // Display results
                        val s = classes[maxPos] + ": " + String.format("%.1f%%", maxConfidence * 100)
                        Toast.makeText(context, s, Toast.LENGTH_LONG).show()

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: 🥲", exception)
            }
            private fun imageProxyToBitmap(image: ImageProxy): Bitmap? {
                val buffer = image.planes[0].buffer
                val bytes = ByteArray(buffer.capacity()) // Increase the capacity of the byte array
                buffer.get(bytes)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            }
        }
    )


}



