package com.example.umc.ui.buy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc.R
import com.example.umc.databinding.FragmentBuyAllBinding
import com.example.umc.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuyAllFragment : Fragment(R.layout.fragment_buy_all) {
    private var _binding: FragmentBuyAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BuyAllViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBuyAllBinding.bind(view)
        binding.rvBuyProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.rvBuyProducts.adapter = ProductAdapter(state.products)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
