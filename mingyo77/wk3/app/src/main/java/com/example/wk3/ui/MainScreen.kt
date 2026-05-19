package com.example.wk3.ui

import com.example.wk3.R
import android.R.attr.padding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavDestination.Companion.hasRoute

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Home::class) } == true,
                    onClick = { navController.navigate(Home) },
                    icon = { Icon(painter = painterResource(R.drawable.ic_housesimple), contentDescription = "Home") },
                    label = { Text("홈") }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Purchase::class) } == true,
                    onClick = { navController.navigate(Purchase) { launchSingleTop = true } },
                    icon = { Icon(painter = painterResource(id = R.drawable.ic_listmagnifyingglass), contentDescription = "구매탭") },
                    label = { Text("구매") }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Wish::class) } == true,
                    onClick = { navController.navigate(Wish) { launchSingleTop = true } },
                    icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) },
                    label = { Text("위시리스트") }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Cart::class) } == true,
                    onClick = { navController.navigate(Cart) { launchSingleTop = true } },
                    icon = { Icon(painter = painterResource(id = R.drawable.ic_bagsimple), contentDescription = null) },
                    label = { Text("장바구니") }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(Profile::class) } == true,
                    onClick = { navController.navigate(Profile) { launchSingleTop = true } },
                    icon = { Icon(painter = painterResource(R.drawable.ic_user), contentDescription = null) },
                    label = { Text("프로필") }
                )


            }
        }
    ){ innerPadding->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.padding(innerPadding)
        ){
            composable<Home>{ HomeScreen() }
            composable<Purchase>{ PurchaseScreen() }
            composable<Wish>{ WishlistScreen() }
            composable<Cart>{
                CartScreen(
                    onNavigateToPurchase = {
                        navController.navigate(Purchase) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Profile>{ ProfileScreen() }
        }
    }
}