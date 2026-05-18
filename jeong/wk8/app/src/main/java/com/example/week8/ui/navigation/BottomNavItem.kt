package com.example.week8.ui.navigation

import com.example.week8.R

// 하단 네비게이션에 들어갈 탭 목록을 한곳에서 관리하기 위함.
// [bottom_menu.xml] 의 menu item 을 enum 으로 표현.
// route -> 이동루트 / label -> 하단 라벨 / icon -> 하단아이콘

enum class BottomNavItem(
    val route: String,
    val label: String,
    val iconRes: Int,
) {
    Home("home", "홈", R.drawable.ic_home),
    Shop("shop", "구매하기", R.drawable.ic_search),
    Wishlist("wishlist", "위시리스트", R.drawable.ic_heart),
    Cart("cart", "장바구니", R.drawable.ic_bag),
    Profile("profile", "프로필", R.drawable.ic_user),
}
