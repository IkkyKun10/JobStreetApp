package com.riezki.jobstreetapp.presenter.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.riezki.jobstreetapp.domain.models.JobsItem

/**
 * @author riezky maisyar
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    jobsList: LazyPagingItems<JobsItem>,
    onClickedItem: (JobsItem) -> Unit
) {
    val context = LocalContext.current
    
    LaunchedEffect(key1 = jobsList.loadState) {
        if (jobsList.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (jobsList.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Job Detail")
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        )

        if (jobsList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = jobsList.itemCount,
                    key = jobsList.itemKey { it.idEntity!! },
                    contentType = jobsList.itemContentType { "contentType" }
                ) { index ->
                    val jobs = jobsList[index]
                    if (jobs != null) {
                        HomeItemScreens(jobsItem = jobs) {
                            onClickedItem(jobs)
                        }
                    }
                }

                item {
                    if(jobsList.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
