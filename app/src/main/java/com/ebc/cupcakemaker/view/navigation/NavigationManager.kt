package com.ebc.cupcakemaker.view.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable

// Crear función para el topbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar() {
    CenterAlignedTopAppBar(
        title = {"Título"},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )

    )

}


// Crear el nav manager
@Composable
fun NavigationManager() {
    val navController = rememberNavController();

    Scaffold(
        topBar = {CupcakeAppBar()},
        bottomBar = {Text(text = "I'm the bottom container")}

    ) {
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            composable("Home") {
                Text(text = "Placeholder Home")
            }
        }
    }

}


