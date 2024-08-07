package com.riezki.jobstreetapp.presenter.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.riezki.jobstreetapp.R
import com.riezki.jobstreetapp.domain.models.JobsItem

/**
 * @author riezkymaisyar
 */

@Composable
fun HomeItemScreens(
    jobsItem: JobsItem,
    onClickedItem: () -> Unit,
) {
    OutlinedCard(
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClickedItem() }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(jobsItem.companyLogo)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .weight(0.5f),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_loading)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 22.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = jobsItem.title.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = jobsItem.company.toString(),
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = jobsItem.location.toString(),
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.5f)
            )
        }
    }
}

@Preview
@Composable
fun HomeItemScreensPreview() {
    HomeItemScreens(
        JobsItem(
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
        ),
    )
    {

    }
}