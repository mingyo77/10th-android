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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wk3.MainActivity
import com.example.wk3.data.DataStoreManager
import com.example.wk3.data.Product
import com.example.wk3.R
import com.example.wk3.ViewModel.PurchaseViewModel
import com.example.wk3.adapter.PurchaseAdapter
import com.example.wk3.databinding.FragmentPurchaseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PurchaseFragment : Fragment() {
    private val viewModel: PurchaseViewModel by viewModels()
    //바인딩 변수 선언
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!
    private lateinit var purchaseAdapter: PurchaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //바인딩 초기화
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        viewModel.loadPurchaseList()
    }
    private fun setupRecyclerView() {

        //어댑터 연결
        purchaseAdapter = PurchaseAdapter(
            purchaseList = ArrayList(),
            onWishClick = { product ->
                (activity as? MainActivity)?.toggleWishItem(product)
            },
            onItemClick = { product ->
            }
        )
        binding.rvPurchase.adapter = purchaseAdapter
        binding.rvPurchase.layoutManager = GridLayoutManager(context, 2)

        purchaseAdapter.setMyItemClickListener(object : PurchaseAdapter.MyItemClickListener {
            override fun onItemClick(product: Product) {
                //데이터를 담을 번들
                val bundle = Bundle().apply {
                    putSerializable("product", product)
                }
                //상세 페이지 생성 및 데이터(번들)전달
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
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.purchaseList.collect { newList ->
                  purchaseAdapter.updateData(newList)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}