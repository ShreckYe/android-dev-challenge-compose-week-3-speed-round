/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.math.round

fun fullAlphaColor(color: Int): Color {
    require(color in 0..0xFFFFFF)
    return Color(0xFF000000 or color.toLong())
}

fun percentAlphaColor(color: Int, alphaPercent: Int): Color {
    require(color in 0..0xFFFFFF)
    require(alphaPercent in 0..100)
    val alpha = round(0xFF.toFloat() * alphaPercent / 100)
    return Color(alpha.toLong() shl 24 or color.toLong())
}

val pink100 = fullAlphaColor(0xFFF1F1)
val pink900 = fullAlphaColor(0x3F2C2C)
val white = Color.White
val white850 = percentAlphaColor(0xFFFFFF, 85)
val gray = fullAlphaColor(0x232323)

val green900 = fullAlphaColor(0x2D3B2D)
val green300 = fullAlphaColor(0xB8C9B8)
val white150 = percentAlphaColor(0xFFFFFF, 15)