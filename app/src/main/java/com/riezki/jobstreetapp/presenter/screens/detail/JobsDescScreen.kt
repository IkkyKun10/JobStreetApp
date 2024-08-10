package com.riezki.jobstreetapp.presenter.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riezki.jobstreetapp.domain.models.JobsItem

/**
 * @author riezkymaisyar
 */

@Composable
fun JobsDescScreen(
    jobsItem: JobsItem,
) {
    Column {
        Text(
            text = "Jobs Description",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        OutlinedCard(
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "Title",
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 4.dp
                )
            )
            Text(
                text = jobsItem.title.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            )
            Text(
                text = "Full time",
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 4.dp
                )
            )
            Text(
                text = jobsItem.type.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            )
            Text(
                text = "Description",
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 4.dp
                )
            )
            Text(
                text = jobsItem.description.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp,
                    end = 8.dp
                )
            )
        }
    }
}

@Preview
@Composable
fun JobsScreenPreview() {
    JobsDescScreen(
        JobsItem(
            "Google",
            "",
            "",
            "",
            """
                Lorem Ipsum adalah contoh teks atau dummy dalam industri percetakan dan penataan huruf atau typesetting. Lorem Ipsum telah menjadi standar contoh teks sejak tahun 1500an, saat seorang tukang cetak yang tidak dikenal mengambil sebuah kumpulan teks dan mengacaknya untuk menjadi sebuah buku contoh huruf. Ia tidak hanya bertahan selama 5 abad, tapi juga telah beralih ke penataan huruf elektronik, tanpa ada perubahan apapun. Ia mulai dipopulerkan pada tahun 1960 dengan diluncurkannya lembaran-lembaran Letraset yang menggunakan kalimat-kalimat dari Lorem Ipsum, dan seiring munculnya perangkat lunak Desktop Publishing seperti Aldus PageMaker juga memiliki versi Lorem Ipsum.
            """.trimIndent(),
            "",
            "",
            "Berlin",
            "Software Engineer",
            "Yes",
            "",
        )
    )
}