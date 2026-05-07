package com.example.umc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc.databinding.FragmentBuyAllBinding
import kotlinx.coroutines.launch

class BuyAllFragment : Fragment() {

    private var _binding: FragmentBuyAllBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(
            productList = emptyList(),
            wishClick = { product ->
                viewLifecycleOwner.lifecycleScope.launch {
                    ProductDataStore.toggleWish(requireContext(), product.id)
                }
            }
        )

        binding.rvBuyProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBuyProducts.adapter = productAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            ProductDataStore.getBuyProducts(requireContext()).collect { productList ->
                productAdapter.updateList(productList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}