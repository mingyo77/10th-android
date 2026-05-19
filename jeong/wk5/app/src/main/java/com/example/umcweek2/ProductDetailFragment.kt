package com.example.umcweek2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.umcweek2.databinding.FragmentProductDetailBinding
import com.example.umcweek2.presentation.detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    private val productId: Int by lazy {
        requireArguments().getInt(ARG_PRODUCT_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.topBar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(v.paddingLeft, topInset, v.paddingRight, v.paddingBottom)
            insets
        }
        observeProduct()
        viewModel.loadProduct(productId)

        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            viewModel.toggleWishlist(productId)
        }
    }

    private fun observeProduct() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.product.collect { product ->
                    if (product == null) return@collect
                    binding.ivProduct.setImageResource(product.imageResId)
                    binding.ivThumb1.setImageResource(product.imageResId)
                    binding.ivThumb2.setImageResource(product.imageResId)
                    binding.ivThumb3.setImageResource(product.imageResId)
                    binding.tvTopTitle.text = product.name
                    binding.tvSubTitle.text = product.subtitle
                    binding.tvName.text = product.name
                    binding.tvPrice.text = product.price
                    binding.tvDescription.text = product.description
                    setWishlistIcon(product)
                }
            }
        }
    }

    private fun setWishlistIcon(product: Product) {
        binding.btnWishlist.icon = AppCompatResources.getDrawable(
            requireContext(),
            if (product.isWishlisted) R.drawable.ic_heart_filled else R.drawable.ic_heart
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PRODUCT_ID = "arg_product_id"

        fun newInstance(productId: Int): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PRODUCT_ID, productId)
                }
            }
        }
    }
}
