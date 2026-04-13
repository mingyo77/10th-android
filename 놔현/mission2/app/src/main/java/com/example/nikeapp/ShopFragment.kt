package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeapp.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStore: ProductDataStore
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStore = ProductDataStore(requireContext())

        val dummyList = mutableListOf(
            ProductData("Nike Everyday Plus", "₩10,000", R.drawable.home_bg),
            ProductData("Nike Elite Crew", "₩16,000", R.drawable.home_bg),
            ProductData("Nike Air Force 1", "₩115,000", R.drawable.home_bg),
            ProductData("Jordan ENike", "₩115,000", R.drawable.home_bg)
        )

        adapter = ProductAdapter(dummyList, onHeartClick = { product ->
            lifecycleScope.launch {
                dataStore.toggleWishlist(product)
            }
        })

        binding.shopProductRv.adapter = adapter
        binding.shopProductRv.layoutManager = GridLayoutManager(requireContext(), 2)

        // 탭 클릭 이벤트
        binding.shopTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // 전체
                        binding.shopProductRv.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, Fragment())
                            .commit()
                    }
                    1 -> { // Tops & T-Shirts
                        binding.shopProductRv.visibility = View.GONE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, TopsTshirtsFragment())
                            .commit()
                    }
                    2 -> { // Sale
                        binding.shopProductRv.visibility = View.GONE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, SaleFragment())
                            .commit()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // DataStore 저장
        lifecycleScope.launch {
            dataStore.getProducts(ProductDataStore.SHOP_PRODUCTS_KEY).collect { savedList ->
                if (savedList.isEmpty()) {
                    dataStore.saveProducts(ProductDataStore.SHOP_PRODUCTS_KEY, dummyList)
                }
            }
        }

        // 위시리스트 상태 업데이트
        lifecycleScope.launch {
            dataStore.getProducts(ProductDataStore.WISHLIST_PRODUCTS_KEY).collect { wishlist ->
                val names = wishlist.map { it.name }.toSet()
                adapter.updateWishlist(names)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}