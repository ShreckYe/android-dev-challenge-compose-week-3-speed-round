package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.imageBitmapFromAssets
import com.example.androiddevchallenge.model.Plant
import com.example.androiddevchallenge.model.Theme
import com.example.androiddevchallenge.model.plants
import com.example.androiddevchallenge.model.themes
import com.example.androiddevchallenge.ui.theme.Elevation
import com.example.androiddevchallenge.ui.theme.dividerColor
import java.util.*

@Composable
fun Home() {
    // It seems that a painter can't be reused multiple times.
    @Composable
    fun placeholderPainter() = painterResource(
        if (isLightTheme) R.drawable.ic_light_logo
        else R.drawable.ic_dark_logo
    )

    Column {
        Spacer(Modifier.height(40.dp))

        var searchQuery by remember { mutableStateOf("") }
        OutlinedTextField(
            searchQuery, { searchQuery = it },
            Modifier.fillMaxWidthWithPadding(),
            placeholder = {
                Text(
                    stringResource(R.string.search),
                    style = MaterialTheme.typography.body1,
                    color = defaultTextColor
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search, stringResource(R.string.search),
                    Modifier.size(18.dp)
                )
            },
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = defaultTextColor)
        )

        Text(
            stringResource(R.string.browse_themes),
            Modifier
                .paddingFromBaseline(top = 32.dp)
                .defaultHorizontalPadding(),
            style = MaterialTheme.typography.h1,
            color = defaultTextColor
        )
        LazyRow(
            Modifier.defaultStartPadding(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(themes) {
                ThemeItem(it)
            }
        }

        var filter by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                stringResource(R.string.design_your_home_garden),
                Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = defaultPadding)
                    .defaultStartPadding(),
                style = MaterialTheme.typography.h1,
                color = defaultTextColor
            )
            Spacer(Modifier.weight(1f))
            IconToggleButton(filter, { filter = it }) {
                Icon(
                    Icons.Default.FilterList, stringResource(R.string.filter),
                    Modifier.size(24.dp)
                )
            }
        }

        val itemsChecked = remember {
            mutableStateListOf<Boolean>().apply { addAll(List(plants.size) { false }) }
        }
        val plantsAndChecked = plants zip itemsChecked
        LazyColumn {
            @Suppress("MoveLambdaOutsideParentheses")
            if (!filter)
                itemsIndexed(plantsAndChecked) { index, (plant, checked) ->
                    PlantItem(plant, checked, { itemsChecked[index] = !checked })
                }
            else
                items(plantsAndChecked.withIndex().filter { it.value.second }) { (index, value) ->
                    val (plant, checked) = value
                    PlantItem(plant, checked, { itemsChecked[index] = !checked })
                }
        }
    }
}

@Composable
fun ThemeItem(theme: Theme) {
    val name = theme.name
    Card(
        Modifier.size(136.dp),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background,
        elevation = Elevation.card
    ) {
        Column {
            Image(
                imageBitmapFromAssets(theme.imageFileName), name,
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                name.capitalize(Locale.getDefault()),
                Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                    .padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.h2,
                color = defaultTextColor
            )

        }
    }
}

//@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlantItem(
    plant: Plant,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)
) {
    // ListItem is not customizable enough.
    Row(Modifier.defaultHorizontalPadding()) {
        Image(
            imageBitmapFromAssets(plant.imageFileName), plant.name,
            Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(halfDefaultPadding))
        Column {
            Row {
                Spacer(Modifier.width(halfDefaultPadding))
                Column(Modifier.weight(1f)) {
                    Text(
                        plant.name.capitalize(Locale.getDefault()),
                        Modifier.paddingFromBaseline(top = 24.dp),
                        style = MaterialTheme.typography.h2,
                        color = defaultTextColor
                    )

                    Text(
                        plant.description,
                        Modifier.paddingFromBaseline(bottom = 24.dp),
                        style = MaterialTheme.typography.body1,
                        color = defaultTextColor
                    )
                }
                Checkbox(
                    checked, onCheckedChange,
                    Modifier.padding(top = defaultPadding, bottom = defaultPadding)
                )
            }
            Divider(color = dividerColor)
        }
    }
}

@Preview("Home - Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeLightPreview() =
    MyAppMyThemePreview {
        Home()
    }

@Preview("Home - Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeDarkPreview() =
    MyAppMyThemePreview(darkTheme = true) {
        Home()
    }