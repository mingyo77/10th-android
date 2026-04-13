package com.example.wk3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.wk3.data.DataStoreManager
import com.example.wk3.data.Product
import com.example.wk3.databinding.ActivityMainBinding
import com.example.wk3.fragment.CartFragment
import com.example.wk3.fragment.HomeFragment
import com.example.wk3.fragment.ProfileFragment
import com.example.wk3.fragment.PurchaseFragment
import com.example.wk3.fragment.WishlistFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStoreManager: DataStoreManager
    //찜한 상품을 담을 리스트
    var wishList = ArrayList<Product>()
    //위시리스트에 상품을 추가/삭제 토글 기능

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        //데이터스토어 매니저 초기화
        dataStoreManager = DataStoreManager(this)
        lifecycleScope.launch{
            dataStoreManager.getProductList().collect{ allProducts->
                //isWish가 true(찜한)값만 wishList에 담기
                wishList.clear()
                wishList.addAll(allProducts.filter { it.isWish })
            }
        }
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
    fun toggleWishItem(product: Product) {
        if (product.isWish) {
            // 리스트에 없는지 확인 후 추가
            if (!wishList.any { it.name == product.name }) {
                wishList.add(product)
            }
        } else {
            // 리스트에서 제거
            wishList.removeAll { it.name == product.name }
        }//DataStore에 변경사항 굽기
        lifecycleScope.launch {
            //DataStore에 있는 리스트를 가져와서, 해당되는 상품의 상태 변경
            val allProducts = dataStoreManager.getProductList().first()
            allProducts.find{ it.name == product.name }?.let{target->
                target.isWish = product.isWish
            }
            //수정된 리스트 저장
            dataStoreManager.saveProductList(allProducts)
        }
    }
}
