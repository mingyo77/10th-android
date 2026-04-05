package com.example.wk3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wk3.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {
    //바인딩 변수 선언
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    //더미 데이터
    private var purchaseDatas = ArrayList<Product>()
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

        purchaseDatas.apply {
            add(Product("조던", "$500", R.drawable.ic_shoes, "This is Jordan"))
            add(Product("나이키", "$500", R.drawable.ic_shoes, "This is Nike"))
            add(Product("아디다스", "$500", R.drawable.ic_shoes, "This is Adidas"))
            add(Product("조던", "$500", R.drawable.ic_shoes, "This is Jordan"))
            add(Product("나이키", "$500", R.drawable.ic_shoes, "This is Nike"))
            add(Product("아디다스", "$500", R.drawable.ic_shoes, "This is Adidas"))
            add(Product("조던", "$500", R.drawable.ic_shoes, "This is Jordan"))
            add(Product("나이키", "$500", R.drawable.ic_shoes, "This is Nike"))
            add(Product("아디다스", "$500", R.drawable.ic_shoes, "This is Adidas"))
            add(Product("조던", "$500", R.drawable.ic_shoes, "This is Jordan"))
            add(Product("나이키", "$500", R.drawable.ic_shoes, "This is Nike"))
            add(Product("아디다스", "$500", R.drawable.ic_shoes, "This is Adidas"))
            add(Product("조던", "$500", R.drawable.ic_shoes, "This is Jordan"))
            add(Product("나이키", "$500", R.drawable.ic_shoes, "This is Nike"))
        }
        //어댑터 연결
        val purchaseAdapter = PurchaseAdapter(purchaseDatas)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}