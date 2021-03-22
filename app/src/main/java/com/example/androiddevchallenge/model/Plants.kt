package com.example.androiddevchallenge.model

val plantNamesAndImageNames = listOf(
    "monstera" to "pexels-huy-phan-3097770.jpg",
    "aglaonema" to "pexels-karolina-grabowska-4751978.jpg",
    "peace lily" to "pexels-melvin-vito-4425201.jpg",
    "fiddle leaf tree" to "pexels-владимир-гладков-6208087.jpg",
    "snake plant" to "pexels-fabian-stroobants-2123482.jpg",
    "pothos" to "pexels-faraz-ahmad-1084199.jpg",
)

data class Plant(val name: String, val description: String, val imageFileName: String)

val plants = plantNamesAndImageNames.map { Plant(it.first, "This is a description", it.second) }