package com.riezki.jobstreetapp.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.riezki.jobstreetapp.presenter.screens.HomeScreen
import com.riezki.jobstreetapp.presenter.ui.theme.JobStreetAppTheme
import com.riezki.jobstreetapp.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author riezky maisyar
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobStreetAppTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "Job Detail")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                        )
                    },
                    modifier = Modifier
                ) { innerPadding ->
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val jobsList = viewModel.jobsPagingFlow.collectAsLazyPagingItems()
                    HomeScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        jobsList = jobsList
                    ) {
                        Toast.makeText(
                            this,
                            "Item Clicked with id ${it.id}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}