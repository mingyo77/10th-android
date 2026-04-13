package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStore: ProductDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStore = ProductDataStore(requireContext())

        val dummyList = mutableListOf(
            ProductData("Air Jordan XXXVI", "₩185,000", R.drawable.home_bg),
            ProductData("Air Jordan 1 Mid", "₩125,000", R.drawable.home_bg),
            ProductData("Nike Air Force 1", "₩115,000", R.drawable.home_bg),
            ProductData("Nike Zoom", "₩139,000", R.drawable.home_bg),
            ProductData("Nike Pegasus", "₩149,000", R.drawable.home_bg)
        )

        val adapter = ProductHorizontalAdapter(dummyList)
        binding.homeProductRv.adapter = adapter
        binding.homeProductRv.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // 홈 최초 진입 시 DataStore에 저장
        lifecycleScope.launch {
            dataStore.getProducts(ProductDataStore.HOME_PRODUCTS_KEY).collect { savedList ->
                if (savedList.isEmpty()) {
                    dataStore.saveProducts(ProductDataStore.HOME_PRODUCTS_KEY, dummyList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}