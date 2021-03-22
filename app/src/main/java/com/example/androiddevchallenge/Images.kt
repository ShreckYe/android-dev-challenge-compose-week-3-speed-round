package com.example.androiddevchallenge

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun imageBitmapFromAssets(fileName: String): ImageBitmap {
    /* This doesn't work:
    val imageFd = LocalContext.current.assets.openFd(fileName).fileDescriptor
    val bitmap = BitmapFactory.decodeFileDescriptor(imageFd)
    return bitmap.asImageBitmap()
    */
    return LocalContext.current.assets.open(fileName).use {
        val bitmap = BitmapFactory.decodeStream(it)
        bitmap.asImageBitmap()
    }
}