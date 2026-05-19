package com.example.week7

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.week7.navigation.AppTab
import com.example.week7.ui.components.AppBottomBar
import com.example.week7.ui.screens.CartScreen
import com.example.week7.ui.screens.HomeScreen
import com.example.week7.ui.screens.ProfileScreen
import com.example.week7.ui.screens.ShopScreen
import com.example.week7.ui.screens.WishlistScreen
import com.example.week7.ui.theme.AppColors
import androidx.compose.runtime.Composable

@Composable
fun Week7App() {
    var selectedOrdinal by rememberSaveable { mutableIntStateOf(AppTab.Home.ordinal) }
    val selectedTab = AppTab.entries[selectedOrdinal]

    Scaffold(
        containerColor = AppColors.White,
        bottomBar = {
            AppBottomBar(
                selected = selectedTab,
                onSelect = { selectedOrdinal = it.ordinal },
                modifier = Modifier.navigationBarsPadding(),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (selectedTab) {
                AppTab.Home -> HomeScreen(Modifier.fillMaxSize())
                AppTab.Shop -> ShopScreen(Modifier.fillMaxSize())
                AppTab.Wishlist -> WishlistScreen(Modifier.fillMaxSize())
                AppTab.Cart -> CartScreen(
                    onOrderClick = { selectedOrdinal = AppTab.Shop.ordinal },
                    modifier = Modifier.fillMaxSize(),
                )
                AppTab.Profile -> ProfileScreen(Modifier.fillMaxSize())
            }
        }
    }
}
