package com.example.umcweek2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.umcweek2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바/내비게이션바 영역까지 레이아웃 확장
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.isItemActiveIndicatorEnabled = false

        applyInsets()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavigationView.selectedItemId = R.id.nav_home
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_shop -> {
                    replaceFragment(ShopFragment())
                    true
                }

                R.id.nav_wishlist -> {
                    replaceFragment(WishFragment())
                    true
                }

                R.id.nav_cart -> {
                    replaceFragment(CartFragment())
                    true
                }

                R.id.nav_profile -> {
                    replaceFragment(UserFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainRoot) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // 하단 네비가 시스템 내비게이션 바와 겹치지 않게
            binding.bottomNavigationView.setPadding(
                binding.bottomNavigationView.paddingLeft,
                binding.bottomNavigationView.paddingTop,
                binding.bottomNavigationView.paddingRight,
                systemBars.bottom
            )

            insets
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun selectBottomTab(menuId: Int) {
        binding.bottomNavigationView.selectedItemId = menuId
    }
}