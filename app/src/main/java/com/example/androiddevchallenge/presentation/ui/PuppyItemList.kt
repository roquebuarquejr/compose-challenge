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
package com.example.androiddevchallenge.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.PuppyDto
import kotlin.math.ceil

@Composable
fun PuppyListItem(puppy: PuppyDto, callback: (Long) -> Unit) {

    Surface(

        modifier = Modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier
                .clickable(
                    onClick = { callback(puppy.id) }
                )

        ) {
            val (image, avatar, subject, name, steps, instagram) = createRefs()
            val coverImage: Painter = painterResource(id = puppy.cover)
            Image(
                painter = coverImage,
                contentDescription = puppy.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )

            val outlineColor = LocalElevationOverlay.current?.apply(
                color = MaterialTheme.colors.surface,
                elevation = 2.dp
            ) ?: MaterialTheme.colors.surface

            OutlinedAvatar(
                puppy = puppy,
                outlineColor = outlineColor,
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(avatar) {
                        centerHorizontallyTo(parent)
                        centerAround(image.bottom)
                    }

            )

            Text(
                text = puppy.name,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(name) {
                        top.linkTo(avatar.bottom)
                    }
            )

            Text(
                text = puppy.breeds,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(subject) {
                        top.linkTo(name.bottom)
                    }
            )

            Text(
                text = "Age: " + puppy.age.toString(),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(steps) {
                        top.linkTo(subject.bottom)
                    }
            )

            Text(
                text = "Insta: " + puppy.instagram,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        bottom = 16.dp
                    )
                    .constrainAs(instagram) {
                        top.linkTo(steps.bottom)
                    }
            )
        }
    }
}

@Composable
fun OutlinedAvatar(
    puppy: PuppyDto,
    modifier: Modifier = Modifier,
    outlineColor: Color = MaterialTheme.colors.surface
) {
    Box(
        modifier = modifier.background(
            color = outlineColor,
            shape = CircleShape
        )
    ) {
        val avatar: Painter = painterResource(id = puppy.image)
        Image(
            painter = avatar,
            contentDescription = puppy.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}

@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    maxColumnWidth: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) {
            "Unbounded width not supported"
        }
        val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
        val columnWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = columnWidth)
        val colHeights = IntArray(columns) { 0 } // track each column's height
        val placeables = measurables.map { measurable ->
            val column = shortestColumn(colHeights)
            val placeable = measurable.measure(itemConstraints)
            colHeights[column] += placeable.height
            placeable
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            val colY = IntArray(columns) { 0 }
            placeables.forEach { placeable ->
                val column = shortestColumn(colY)
                placeable.place(
                    x = columnWidth * column,
                    y = colY[column]
                )
                colY[column] += placeable.height
            }
        }
    }
}

private fun shortestColumn(colHeights: IntArray): Int {
    var minHeight = Int.MAX_VALUE
    var column = 0
    colHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            column = index
        }
    }
    return column
}

@Preview(showBackground = true)
@Composable
fun SamplePuppyListItem() {
    PuppyListItem(puppy = PuppyDto.getPuppies().first(), callback = {})
}
