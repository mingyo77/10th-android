package com.example.wk3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wk3.databinding.FragmentWishlistBinding


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

        // 메인액티비티에서 wishList를 가져옴
        val mainActivity = activity as MainActivity

        // 어댑터 연결
        val wishAdapter = ProductAdapter(mainActivity.wishList)
        binding.rvWishlist.adapter = wishAdapter
        binding.rvWishlist.layoutManager = GridLayoutManager(context, 2)
        wishAdapter.setMyItemClickListener(object : ProductAdapter.MyItemClickListener {
            override fun onItemClick(product: Product) {
                // 데이터를 담을 번들
                val bundle = Bundle().apply {
                    putSerializable("product", product)
                }

                // 상세 페이지 생성 및 데이터 전달
                val detailFragment = DetailFragment().apply {
                    arguments = bundle
                }

                // 화면 전환
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