package com.example.umc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc.databinding.ActivityMainBinding
import com.example.umc.ui.buy.BuyFragment
import com.example.umc.ui.cart.CartFragment
import com.example.umc.ui.home.HomeFragment
import com.example.umc.ui.profile.ProfileFragment
import com.example.umc.ui.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> replaceFragment(HomeFragment())
                R.id.menu_buy -> replaceFragment(BuyFragment())
                R.id.menu_wishlist -> replaceFragment(WishlistFragment())
                R.id.menu_cart -> replaceFragment(CartFragment())
                R.id.menu_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
