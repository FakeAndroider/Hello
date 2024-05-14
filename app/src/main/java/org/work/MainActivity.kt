package org.work

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.work.ui.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { CustomBottomBar(navController) }
        ) {
            Navigation(navController)
        }
    }

    @Composable
    private fun Navigation(navController: NavHostController) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Notifications.route) { NotificationsScreen() }
        }
    }

}

