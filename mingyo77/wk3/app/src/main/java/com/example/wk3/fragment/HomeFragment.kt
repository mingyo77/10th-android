package com.example.wk3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wk3.data.DataStoreManager
import com.example.wk3.data.Product
import com.example.wk3.R
import com.example.wk3.ViewModel.HomeViewModel
import com.example.wk3.adapter.ProductAdapter
import com.example.wk3.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    //신발 데이터 담을 리스트 변수 선언
    private var productDatas = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        viewModel.loadProducts()
    }
    private fun setupRecyclerView(){
        productAdapter = ProductAdapter(productDatas)
        binding.rvHomeProduct.adapter = productAdapter
        binding.rvHomeProduct.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        productAdapter.setMyItemClickListener(object : ProductAdapter.MyItemClickListener {
            override fun onItemClick(product: Product) {
                //데이터를 담을 번들
                val bundle = Bundle().apply {
                    putSerializable("product", product)
                }
                //상세 페이지 생성 및 데이터 전달
                val detailFragment = DetailFragment().apply {
                    arguments = bundle
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }
    private fun observeViewModel(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.productList.collect{products->
                    Log.d("HomeFragment_Test","뷰모델에서 넘어온 데이터개수: \${products.size}")
                    productDatas.clear()
                    productDatas.addAll(products)
                    productAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}