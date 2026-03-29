package com.example.umc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CartFragment.CartButtonClickListener {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val buyFragment = BuyFragment()
    private val wishlistFragment = WishlistFragment()
    private val cartFragment = CartFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()

        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.menu_home
            replaceFragment(homeFragment)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.menu_buy -> {
                    replaceFragment(buyFragment)
                    true
                }
                R.id.menu_wishlist -> {
                    replaceFragment(wishlistFragment)
                    true
                }
                R.id.menu_cart -> {
                    replaceFragment(cartFragment)
                    true
                }
                R.id.menu_profile -> {
                    replaceFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onOrderButtonClicked() {
        binding.bottomNavigationView.selectedItemId = R.id.menu_buy
    }
}