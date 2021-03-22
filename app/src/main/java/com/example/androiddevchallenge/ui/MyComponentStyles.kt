package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.white

val defaultPadding = 16.dp

fun Modifier.defaultPadding() =
    padding(defaultPadding)

fun Modifier.defaultHorizontalPadding() =
    padding(start = defaultPadding, end = defaultPadding)

fun Modifier.defaultStartPadding() =
    padding(start = defaultPadding)

val halfDefaultPadding = 8.dp

fun Modifier.fillMaxWidthWithPadding() =
    fillMaxWidth()
        .defaultHorizontalPadding()

fun Modifier.myRoundButtonModifier() =
    fillMaxWidthWithPadding()
        .height(48.dp)

@Composable
fun MyRoundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) =
    Button(
        onClick = onClick,
        modifier = modifier.myRoundButtonModifier(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        content = content
    )

val isLightTheme @Composable get() = MaterialTheme.colors.isLight
val defaultTextColor @Composable get() = if (isLightTheme) gray else white