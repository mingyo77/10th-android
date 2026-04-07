package com.example.umc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(
            productList = emptyList(),
            itemClick = {
                (activity as? MainActivity)?.moveToBuyTab()
            },
            wishClick = { product ->
                viewLifecycleOwner.lifecycleScope.launch {
                    ProductDataStore.toggleWish(requireContext(), product.id)
                }
            }
        )

        binding.rvWishlistProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWishlistProducts.adapter = productAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            ProductDataStore.getWishlistProducts(requireContext()).collect { wishList ->
                productAdapter.updateList(wishList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}