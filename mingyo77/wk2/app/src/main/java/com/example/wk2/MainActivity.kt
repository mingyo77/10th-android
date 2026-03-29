package com.example.wk2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.wk2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, HomeFragment())
            .commit()

        binding.bottomNav.setOnItemSelectedListener{ item->
            when(item.itemId){
                R.id.homeFragment -> { setFragment(HomeFragment()); true }
                R.id.cartFragment -> { setFragment(CartFragment()); true }
                R.id.profileFragment -> { setFragment(ProfileFragment()); true }
                R.id.wishlistFragment -> { setFragment(WishlistFragment()); true }
                R.id.purchaseFragment -> { setFragment(PurchaseFragment()); true }
            else -> false
            }
        }

    }
    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
    fun updateBottomMenu(itemId: Int){
        binding.bottomNav.selectedItemId = itemId
    }
}