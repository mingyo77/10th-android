package com.example.week8.ui

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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.week8.ui.navigation.BottomNavItem
import com.example.week8.ui.screen.CartScreen
import com.example.week8.ui.screen.HomeScreen
import com.example.week8.ui.screen.ProfileScreen
import com.example.week8.ui.screen.ShopScreen
import com.example.week8.ui.screen.WishlistScreen
import com.example.week8.viewmodel.ProductViewModel

// [MainActivity] + BottomNavigationView + FragmentContainer 를 Compose로 대체함.

@Composable
fun NikeApp(
    // Activity 레벨에서 ViewModel 하나를 공유 → 탭 이동해도 위시 상태 유지
    viewModel: ProductViewModel = viewModel(),
) {
    // NavController: 현재 어떤 화면(route)인지 관리. wk5 FragmentManager 역할
    val navController = rememberNavController()

    // 현재 백스택 최상단 destination 을 State로 관찰 → 하단 탭 selected 표시에 사용
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        // bottomBar: wk5 activity_main.xml 의 BottomNavigationView
        bottomBar = {
            NavigationBar {
                BottomNavItem.entries.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route, // 현재 route와 같으면 선택 스타일
                        onClick = {
                            navController.navigate(item.route) {
                                // popUpTo: 백스택을 시작 화면까지 정리 (탭마다 Fragment 쌓이지 않게)
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true // 탭 전환 시 화면 상태(스크롤 등) 저장
                                }
                                launchSingleTop = true // 같은 탭 재클릭 시 중복 화면 방지
                                restoreState = true // 이전에 saveState 한 내용 복원
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.iconRes),
                                contentDescription = item.label,
                            )
                        },
                        label = { Text(item.label) },
                    )
                }
            }
        },
    ) { innerPadding ->
        // innerPadding: 시스템 바·하단 네비 높이만큼 패딩 (콘텐츠가 네비에 가리지 않게)
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route, // wk5 최초 replaceFragment(HomeFragment())
            modifier = Modifier.padding(innerPadding),
        ) {
            // composable(route): route 문자열에 해당하는 화면 Composable 등록
            composable(BottomNavItem.Home.route) {
                HomeScreen(viewModel = viewModel)
            }
            composable(BottomNavItem.Shop.route) {
                ShopScreen(viewModel = viewModel)
            }
            composable(BottomNavItem.Wishlist.route) {
                WishlistScreen(viewModel = viewModel)
            }
            composable(BottomNavItem.Cart.route) {
                CartScreen(
                    onNavigateToShop = {
                        navController.navigate(BottomNavItem.Shop.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
