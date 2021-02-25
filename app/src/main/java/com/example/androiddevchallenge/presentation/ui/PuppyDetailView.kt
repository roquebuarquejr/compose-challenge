package com.example.androiddevchallenge.presentation.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.PuppyDto
import com.example.androiddevchallenge.presentation.PuppyListViewModel
import java.lang.IllegalArgumentException

@Composable
fun PuppyDetailView(puppyId: Long) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val viewModel: PuppyListViewModel = viewModel()
        val data =
            viewModel.puppies.value?.find { it.id == puppyId } ?: throw  IllegalArgumentException()
        val image: Painter = painterResource(id = data.image)
        Image(
            painter = image,
            contentDescription = data.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = data.description,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}