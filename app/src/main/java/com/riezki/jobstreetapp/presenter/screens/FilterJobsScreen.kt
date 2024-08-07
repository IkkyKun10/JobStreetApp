package com.riezki.jobstreetapp.presenter.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author riezkymaisyar
 */

@Composable
fun FilterJobsScreen(
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
    ) {
        var textLoc by remember { mutableStateOf("") }
        var textDescsFilter by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(text = "Fulltime")
            }
            Text(
                text = "Location",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = textLoc,
                onValueChange = {
                    textLoc = it
                },
                modifier = Modifier
                    .padding(top = 4.dp)
                    .height(24.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Description",
                modifier = Modifier.padding(top = 8.dp)
            )
            OutlinedTextField(
                value = textDescsFilter,
                onValueChange = {
                    textDescsFilter = it
                },
                modifier = Modifier
                    .padding(top = 4.dp)
                    .height(24.dp)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(120.dp, 50.dp)
                    .align(Alignment.End),
                onClick = { /*TODO*/ },

            ) {
                Text(text = "Apply Filter", fontSize = 12.sp)
            }
        }
    }
}

@Preview
@Composable
fun FilterJobsScreenPrev() {
    FilterJobsScreen(modifier = Modifier)
}