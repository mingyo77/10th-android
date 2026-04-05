package com.example.wk3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wk3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        //더미 데이터
        productDatas.apply{
            add(Product("Air Jordan XXXVI", "$200", R.drawable.ic_shoes))
            add(Product("Nike Air Max", "$200", R.drawable.ic_shoes))
            add(Product("Nike", "$200", R.drawable.ic_shoes))
            add(Product("Adidas", "$200", R.drawable.ic_shoes))
            add(Product("Nike2", "$200", R.drawable.ic_shoes))
            add(Product("Adidas2", "$200", R.drawable.ic_shoes))
        }
        val productAdapter = ProductAdapter(productDatas)
        binding.rvHomeProduct.adapter = productAdapter
        binding.rvHomeProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

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

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}