package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white

@Composable
fun Welcome(navController: NavController) {
    Image(
        painterResource(
            if (isLightTheme) R.drawable.ic_light_welcome_bg
            else R.drawable.ic_dark_welcome_bg
        ),
        stringResource(R.string.welcome_background),
        Modifier.fillMaxSize()
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(
                if (isLightTheme) R.drawable.ic_light_welcome_illos
                else R.drawable.ic_dark_welcome_illos
            ),
            stringResource(R.string.welcome_illos),
            Modifier.padding(start = 88.dp, top = 72.dp, bottom = 32.dp)
        )

        Image(
            painterResource(if (isLightTheme) R.drawable.ic_light_logo else R.drawable.ic_dark_logo),
            stringResource(R.string.logo)
        )
        Text(
            stringResource(R.string.bloom_description),
            Modifier.paddingFromBaseline(top = 32.dp, bottom = 40.dp),
            style = MaterialTheme.typography.subtitle1,
            color = defaultTextColor
        )

        Spacer(Modifier.height(40.dp))

        MyRoundButton(
            onClick = { /* not implemented */ },
            Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                stringResource(R.string.create_account),
                style = MaterialTheme.typography.button,
                color = if (isLightTheme) white else gray
            )
        }

        TextButton(onClick = { navController.navigate("log_in") }) {
            Text(
                stringResource(R.string.log_in),
                style = MaterialTheme.typography.button,
                color = if (isLightTheme) pink900 else white
            )
        }
    }
}

@Preview("Welcome - Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeLightPreview() =
    MyAppMyThemePreview {
        Welcome(rememberNavController())
    }

@Preview("Welcome - Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeDarkPreview() =
    MyAppMyThemePreview(darkTheme = true) {
        Welcome(rememberNavController())
    }