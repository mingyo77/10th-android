package com.example.wk3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.wk3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //찜한 상품을 담을 리스트
    var wishList = ArrayList<Product>()
    //위시리스트에 상품을 추가/삭제 토글 기능
    fun toggleWishItem(product: Product) {
        if (product.isWish) {
            // 리스트에 없는지 확인 후 추가
            if (!wishList.any { it.name == product.name }) {
                wishList.add(product)
            }
        } else {
            // 리스트에서 제거
            wishList.removeAll { it.name == product.name }
        }
    }
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
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, HomeFragment())
                .commitNow()
        }
        binding.bottomNav.setOnItemSelectedListener { item ->
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
