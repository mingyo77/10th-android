package com.example.week7.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.week7.R

enum class AppTab(
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
) {
    Home(R.string.tab_home, R.drawable.ic_home),
    Shop(R.string.tab_shop, R.drawable.ic_search),
    Wishlist(R.string.tab_wishlist, R.drawable.ic_heart),
    Cart(R.string.tab_cart, R.drawable.ic_bag),
    Profile(R.string.tab_profile, R.drawable.ic_user),
}
