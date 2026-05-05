package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikeapp.databinding.FragmentWishlistBinding
import com.example.nikeapp.viewmodel.WishlistViewModel
import com.example.nikeapp.viewmodel.WishlistViewModelFactory
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WishlistViewModel by viewModels {
        WishlistViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadWishlist()

        lifecycleScope.launch {
            viewModel.wishlist.collect { wishlist ->
                val adapter = ProductAdapter(wishlist.toMutableList(), onHeartClick = { product ->
                    viewModel.toggleWishlist(product)
                })
                adapter.updateWishlist(wishlist.map { it.name }.toSet())
                binding.wishlistRv.adapter = adapter
                binding.wishlistRv.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}