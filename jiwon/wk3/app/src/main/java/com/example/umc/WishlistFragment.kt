package com.example.umc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishProductList = listOf(
            Product(R.drawable.shoe, "Air Jordan 1 Mid", "US$125", "", true),
            Product(R.drawable.sock, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks", true)
        )

        binding.rvWishlistProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWishlistProducts.adapter = ProductAdapter(wishProductList) { clickedProduct ->
            (activity as? MainActivity)?.moveToBuyTab()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}