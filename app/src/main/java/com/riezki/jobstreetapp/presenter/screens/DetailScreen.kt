package com.riezki.jobstreetapp.presenter.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.presenter.screens.detail.JobsDescScreen
import com.riezki.jobstreetapp.presenter.screens.detail.TopDetailScreen

/**
 * @author riezky maisyar
 */

@Composable
fun DetailScreen(jobsItem: JobsItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(14.dp),
    ) {
        TopDetailScreen(jobsItem = jobsItem)
        Spacer(modifier = Modifier.height(8.dp))
        JobsDescScreen(jobsItem = jobsItem)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPrev() {
    DetailScreen(
        jobsItem = JobsItem(
            "Google",
            "",
            "",
            "",
            "",
            "",
            "",
            "Berlin",
            "Data Engineer",
            "",
            "",
        )
    )
}