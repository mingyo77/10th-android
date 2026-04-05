package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeapp.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStore: ProductDataStore
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStore = ProductDataStore(requireContext())

        adapter = ProductAdapter(mutableListOf(), onHeartClick = { product ->
            lifecycleScope.launch {
                dataStore.toggleWishlist(product)
            }
        })

        binding.wishlistRv.adapter = adapter
        binding.wishlistRv.layoutManager = GridLayoutManager(requireContext(), 2)

        // 위시리스트 실시간 관찰
        lifecycleScope.launch {
            dataStore.getProducts(ProductDataStore.WISHLIST_PRODUCTS_KEY).collect { wishlist ->
                val newList = wishlist.toMutableList()
                adapter = ProductAdapter(newList, onHeartClick = { product ->
                    lifecycleScope.launch {
                        dataStore.toggleWishlist(product)
                    }
                })
                adapter.updateWishlist(wishlist.map { it.name }.toSet())
                binding.wishlistRv.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}