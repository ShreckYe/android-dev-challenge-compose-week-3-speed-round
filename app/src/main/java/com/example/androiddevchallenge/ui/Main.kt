package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(Modifier.height(56.dp)) {
            @Composable
            fun MyBottomNavigationItem(
                selected: Boolean,
                onClick: () -> Unit,
                iconImageVector: ImageVector, labelText: String
            ) = BottomNavigationItem(selected, onClick,
                icon = {
                    Icon(
                        iconImageVector, labelText,
                        Modifier.size(24.dp),
                    )
                },
                label = {
                    Text(
                        labelText,
                        style = MaterialTheme.typography.caption,
                        color = defaultTextColor
                    )
                })

            MyBottomNavigationItem(
                true, {
                    navController.navigate("home") {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                },
                Icons.Default.Home, stringResource(R.string.home)
            )
            MyBottomNavigationItem(
                false, {},
                Icons.Default.FavoriteBorder, stringResource(R.string.favorite)
            )
            MyBottomNavigationItem(
                false, {},
                Icons.Default.AccountCircle, stringResource(R.string.profile)
            )
            MyBottomNavigationItem(
                false, {},
                Icons.Default.ShoppingCart, stringResource(R.string.cart)
            )
        }
    }) {
        NavHost(navController, startDestination = "home") {
            composable("home") { Home() }
        }
    }
}

@Preview("Main - Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainLightPreview() =
    MyAppMyThemePreview {
        Main()
    }

@Preview("Main - Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainDarkPreview() =
    MyAppMyThemePreview(darkTheme = true) {
        Main()
    }