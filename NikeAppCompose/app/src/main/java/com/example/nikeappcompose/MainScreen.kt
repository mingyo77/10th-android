package com.example.nikeappcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nikeappcompose.screen.CartScreen
import com.example.nikeappcompose.screen.HomeScreen
import com.example.nikeappcompose.screen.ProfileScreen
import com.example.nikeappcompose.screen.ShopScreen
import com.example.nikeappcompose.screen.WishlistScreen

data class BottomNavItem(
    val label: String,
    val destination: AppDestination,
    val icon: Int
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        BottomNavItem("홈", AppDestination.Home, R.drawable.ic_home),
        BottomNavItem("구매하기", AppDestination.Shop, R.drawable.ic_shop),
        BottomNavItem("위시리스트", AppDestination.Wishlist, R.drawable.ic_wishlist),
        BottomNavItem("장바구니", AppDestination.Cart, R.drawable.ic_cart),
        BottomNavItem("프로필", AppDestination.Profile, R.drawable.ic_profile),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hasRoute(item.destination::class) == true,
                        onClick = {
                            navController.navigate(item.destination) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestination.Home> { HomeScreen() }
            composable<AppDestination.Shop> { ShopScreen() }
            composable<AppDestination.Wishlist> { WishlistScreen() }
            composable<AppDestination.Cart> {
                CartScreen(
                    onNavigateToShop = {
                        navController.navigate(AppDestination.Shop) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<AppDestination.Profile> { ProfileScreen() }
        }
    }
}