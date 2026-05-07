package com.example.umcweek2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umcweek2.databinding.FragmentWishBinding
import com.example.umcweek2.presentation.wish.WishViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.wishRoot) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(
                v.paddingLeft,
                topInset + 12,
                v.paddingRight,
                v.paddingBottom
            )
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadWishItems()
    }

    private fun setupRecyclerView() {
        binding.rvWishProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wishItems.collect { wishItems ->
                    binding.rvWishProducts.adapter = ShopProductAdapter(
                        items = wishItems,
                        showWishlistButton = false,
                        onClickItem = { item ->
                            (activity as? MainActivity)?.navigateToProductDetail(item.id)
                        },
                        onClickWishlist = { }
                    )
                    binding.tvEmptyWish.visibility = if (wishItems.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
        viewModel.loadWishItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}