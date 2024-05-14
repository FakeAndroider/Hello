package org.work.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.work.R
import org.work.effect.rippleClickable
import androidx.compose.material3.MaterialTheme as MaterialTheme3

@Composable
fun CustomBottomBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Dashboard,
        Screen.Notifications,
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        items.forEach { screen ->
            val weight = 1f / items.size
            BottomNavigationItem(
                modifier = Modifier.weight(weight),
                screen = screen,
                isSelected = navController.currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }

    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    screen: Screen,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) Color.Blue else Color.Gray

    Column(
        modifier = modifier
            .padding(8.dp)
            .rippleClickable(
                onClick = onClick,

                ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = screen.icon),
            contentDescription = screen.route,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = screen.route,
            color = color,
            style = MaterialTheme3.typography.bodySmall
        )
    }
}

sealed class Screen(val route: String, val resourceId: Int, val icon: Int) {
    data object Home : Screen("home", R.string.home, R.drawable.ic_home)
    data object Dashboard : Screen("dashboard", R.string.dashboard, R.drawable.ic_dashboard)
    data object Notifications :
        Screen("notifications", R.string.notifications, R.drawable.ic_notifications)
}

