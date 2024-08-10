package com.riezki.jobstreetapp.presenter.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riezki.jobstreetapp.presenter.viewmodel.HomeViewModel

/**
 * @author riezkymaisyar
 */

@Composable
fun FilterJobsScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel?
) {
    OutlinedCard(
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Fulltime")
                Switch(
                    checked = viewModel?.isSearching ?: false,
                    onCheckedChange = {
                        viewModel?.isSearching = it
                    },
                )
            }
            Text(
                text = "Location",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel?.textLoc ?: "",
                onValueChange = {
                    viewModel?.textLoc = it
                },
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Description",
                modifier = Modifier.padding(top = 8.dp)
            )
            OutlinedTextField(
                value = viewModel?.textDescFilter ?: "",
                onValueChange = {
                    viewModel?.textDescFilter = it
                },
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(120.dp, 50.dp)
                    .align(Alignment.End),
                onClick = {
                    viewModel?.onToogleSearch(
                        viewModel.textDescFilter,
                        viewModel.textLoc,
                        viewModel.isSearching
                    )
                },
            ) {
                Text(text = "Apply Filter", fontSize = 12.sp)
            }
        }
    }
}

@Preview
@Composable
fun FilterJobsScreenPrev() {
    FilterJobsScreen(
        modifier = Modifier,
        viewModel = null
    )
}