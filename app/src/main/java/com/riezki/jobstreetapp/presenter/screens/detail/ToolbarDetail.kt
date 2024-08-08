package com.riezki.jobstreetapp.presenter.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author riezkymaisyar
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDetail(
    onclickedBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Job Detail")
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        navigationIcon = {
            IconButton(onClick = { onclickedBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Preview
@Composable
fun TopBarDetailPreview() {
    ToolbarDetail({})
}