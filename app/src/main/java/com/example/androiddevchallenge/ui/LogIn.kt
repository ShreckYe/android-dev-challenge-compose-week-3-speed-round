package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.white

@Composable
fun LogIn(navController: NavController) {
    val isLightTheme = MaterialTheme.colors.isLight

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stringResource(R.string.log_in_with_email),
            Modifier.paddingFromBaseline(top = 184.dp),
            style = MaterialTheme.typography.h1,
            color = defaultTextColor
        )

        var emailAddress by remember { mutableStateOf("") }
        LogInTextField(
            emailAddress, { emailAddress = it },
            stringResource(R.string.email_address)
        )


        var password by remember { mutableStateOf("") }
        LogInTextField(
            password, { password = it },
            stringResource(R.string.password_placeholder),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.log_in_message))
                val termsOfUseStart = integerResource(R.integer.terms_of_use_start)
                val termsOfUseEnd = integerResource(R.integer.terms_of_use_end)
                addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    termsOfUseStart, termsOfUseEnd
                )
                addStringAnnotation(
                    "terms_of_service", "",
                    termsOfUseStart, termsOfUseEnd
                )

                val privacyPolicyStart = integerResource(R.integer.privacy_policy_start)
                val privacyPolicyEnd = integerResource(R.integer.privacy_policy_end)
                addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    privacyPolicyStart, privacyPolicyEnd
                )
                addStringAnnotation(
                    "privacy_policy", "",
                    privacyPolicyStart, privacyPolicyEnd
                )
            },
            Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                .fillMaxWidthWithPadding(),
            style = MaterialTheme.typography.body2,
            color = defaultTextColor
        )

        MyRoundButton(onClick = { navController.navigate("main") }) {
            Text(
                stringResource(R.string.log_in),
                style = MaterialTheme.typography.button,
                color = if (isLightTheme) white else gray
            )
        }
    }
}

@Composable
fun LogInTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value, onValueChange,
        placeholder = {
            Text(
                placeholderText,
                style = MaterialTheme.typography.body1,
                color = defaultTextColor
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = true,

        modifier = Modifier
            .height(56.dp)
            .fillMaxWidthWithPadding(),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = defaultTextColor)
    )
}

@Preview("Login - Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginInLightPreview() =
    MyAppMyThemePreview {
        LogIn(rememberNavController())
    }

@Preview("Welcome - Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginInDarkPreview() =
    MyAppMyThemePreview(darkTheme = true) {
        LogIn(rememberNavController())
    }