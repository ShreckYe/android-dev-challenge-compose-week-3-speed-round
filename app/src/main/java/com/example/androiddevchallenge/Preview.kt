package com.example.androiddevchallenge.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun MyAppMyThemePreview(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MyTheme(darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}