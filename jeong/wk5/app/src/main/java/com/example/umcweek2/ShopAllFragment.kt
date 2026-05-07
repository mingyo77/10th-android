package com.example.umcweek2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umcweek2.databinding.FragmentShopAllBinding
import com.example.umcweek2.presentation.shop.ShopAllViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShopAllViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvShopProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        observeProducts()
        viewModel.loadProducts()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProducts()
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { products ->
                    binding.rvShopProducts.adapter = ShopProductAdapter(
                        items = products,
                        showWishlistButton = true,
                        onClickItem = { item ->
                            (activity as? MainActivity)?.navigateToProductDetail(item.id)
                        },
                        onClickWishlist = { item ->
                            viewModel.toggleWishlist(item.id)
                        }
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
