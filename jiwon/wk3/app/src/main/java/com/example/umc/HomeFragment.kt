package com.example.umc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeProductList = listOf(
            Product(R.drawable.shoe, "Air Jordan XXVI", "US$185"),
            Product(R.drawable.whiteshoe, "Nike Air Force 1", "US$115"),
            Product(R.drawable.sock, "Nike Everyday Plus", "US$10")
        )

        binding.rvHomeProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeProducts.adapter = ProductAdapter(homeProductList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}