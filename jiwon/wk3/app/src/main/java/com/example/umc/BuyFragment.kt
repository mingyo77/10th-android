package com.example.umc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buyProductList = listOf(
            Product(R.drawable.sock, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks (6 Pairs)", false),
            Product(R.drawable.sock, "Nike Elite Crew", "US$16", "Basketball Socks", true),
            Product(R.drawable.whiteshoe, "Nike Air Force 1 '07", "US$115", "Women's Shoes", false),
            Product(R.drawable.whiteshoe, "Jordan Ernie Air Force 1 '07", "US$115", "Men's Shoes", false)
        )

        binding.rvBuyProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBuyProducts.adapter = ProductAdapter(buyProductList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}