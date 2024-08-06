package com.riezki.jobstreetapp.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobStreetAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val jobsList = viewModel.jobsPagingFlow.collectAsLazyPagingItems()
                    HomeScreen(jobsList = jobsList) {
                        Toast.makeText(this, "Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}