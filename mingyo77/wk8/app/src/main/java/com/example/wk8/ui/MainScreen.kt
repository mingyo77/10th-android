package com.example.wk8.ui

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wk8.R
import com.example.wk8.ui.navigation.Cart
import com.example.wk8.ui.navigation.Home
import com.example.wk8.ui.navigation.Profile
import com.example.wk8.ui.navigation.Shop
import com.example.wk8.ui.navigation.Wish
import com.example.wk8.ui.screen.CartScreen
import com.example.wk8.ui.screen.HomeScreen
import com.example.wk8.ui.screen.ProfileScreen
import com.example.wk8.ui.screen.ShopScreen
import com.example.wk8.ui.screen.WishlistScreen
import com.example.wk8.viewmodel.ProductViewModel

@Composable
fun MainScreen(
    viewModel: ProductViewModel = viewModel(),
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Home::class) } == true,
                    onClick = { navController.navigate(Home) { launchSingleTop = true } },
                    icon = { Icon(painterResource(R.drawable.ic_housesimple), contentDescription = "Home") },
                    label = { Text("홈") },
                )
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Shop::class) } == true,
                    onClick = { navController.navigate(Shop) { launchSingleTop = true } },
                    icon = { Icon(painterResource(R.drawable.ic_listmagnifyingglass), contentDescription = "Shop") },
                    label = { Text("구매") },
                )
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Wish::class) } == true,
                    onClick = { navController.navigate(Wish) { launchSingleTop = true } },
                    icon = { Icon(painterResource(R.drawable.ic_heartstraight), contentDescription = "Wish") },
                    label = { Text("위시리스트") },
                )
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Cart::class) } == true,
                    onClick = { navController.navigate(Cart) { launchSingleTop = true } },
                    icon = { Icon(painterResource(R.drawable.ic_bagsimple), contentDescription = "Cart") },
                    label = { Text("장바구니") },
                )
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Profile::class) } == true,
                    onClick = { navController.navigate(Profile) { launchSingleTop = true } },
                    icon = { Icon(painterResource(R.drawable.ic_user), contentDescription = "Profile") },
                    label = { Text("프로필") },
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable<Home> {
                HomeScreen(viewModel = viewModel)
            }
            composable<Shop> {
                ShopScreen(viewModel = viewModel)
            }
            composable<Wish> {
                WishlistScreen(viewModel = viewModel)
            }
            composable<Cart> {
                CartScreen(
                    onNavigateToShop = {
                        navController.navigate(Shop) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable<Profile> {
                ProfileScreen()
            }
        }
    }
}
