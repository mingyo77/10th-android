package com.example.umc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.umc.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CartFragment.CartButtonClickListener {

    private lateinit var binding: ActivityMainBinding

    fun moveToBuyTab() {
        binding.bottomNavigationView.selectedItemId = R.id.menu_buy
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            ProductDataStore.seedInitialData(this@MainActivity)
        }

        initBottomNavigation()

        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.menu_home
            replaceFragment(HomeFragment())
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_buy -> {
                    replaceFragment(BuyFragment())
                    true
                }
                R.id.menu_wishlist -> {
                    replaceFragment(WishlistFragment())
                    true
                }
                R.id.menu_cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
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