package com.riezki.jobstreetapp.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.riezki.jobstreetapp.R
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.presenter.screens.DetailScreen
import com.riezki.jobstreetapp.presenter.screens.HomeScreen
import com.riezki.jobstreetapp.presenter.ui.theme.JobStreetAppTheme
import com.riezki.jobstreetapp.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

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
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            val viewModel = hiltViewModel<HomeViewModel>()
                            val jobsList = viewModel.jobsPagingFlow.collectAsLazyPagingItems()
                            HomeScreen(
                                modifier = Modifier
                                    .padding(innerPadding),
                                jobsList = jobsList,
                                viewModel = viewModel
                            ) {
                                navController.navigate(
                                    "detailhome/${it.id}"
                                )
                            }
                        }
                        composable(
                            "detailhome/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) { navBackStackEntry ->
                            val id = navBackStackEntry.arguments?.getString("id")
                            DetailScreen(message = "$id", modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object DetailHome : Screen("detail_home")
}