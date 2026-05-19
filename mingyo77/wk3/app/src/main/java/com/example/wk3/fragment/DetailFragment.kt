package com.example.wk3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.wk3.ViewModel.DetailViewModel
import com.example.wk3.data.Product
import com.example.wk3.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 전달받은 데이터 꺼내서 ViewModel에 넘겨주기
        val product = arguments?.getSerializable("product") as? Product
        product?.let { viewModel.setProduct(it) }

        // 2. ViewModel의 데이터를 관찰(Observe)하기
        observeViewModel()
    }
    private fun observeViewModel() {
        // ViewModel에 있는 product 데이터 상자를 지켜봅니다.
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.product.collect { product ->
                    // 데이터가 들어오거나 바뀌면 여기서 UI를 업데이트합니다!
                    product?.let {
                        binding.imageView.setImageResource(it.image)
                        binding.tvDetailProductName.text = it.name
                        binding.tvDetailProductPrice.text = it.price
                        binding.tvDetailProductDescript.text = it.explain
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}