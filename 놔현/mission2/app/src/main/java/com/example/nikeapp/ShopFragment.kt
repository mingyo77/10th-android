package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeapp.databinding.FragmentShopBinding
import com.example.nikeapp.viewmodel.ShopViewModel
import com.example.nikeapp.viewmodel.ShopViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductAdapter
    private val viewModel: ShopViewModel by viewModels {
        ShopViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(viewModel.dummyList, onHeartClick = { product ->
            viewModel.toggleWishlist(product)
        })

        binding.shopProductRv.adapter = adapter
        binding.shopProductRv.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.loadWishlist()
        viewModel.saveShopProducts()

        lifecycleScope.launch {
            viewModel.wishlistNames.collect { names ->
                adapter.updateWishlist(names)
            }
        }

        binding.shopTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.shopProductRv.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, Fragment())
                            .commit()
                    }
                    1 -> {
                        binding.shopProductRv.visibility = View.GONE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, TopsTshirtsFragment())
                            .commit()
                    }
                    2 -> {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}