package com.example.wk2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigator
import com.example.wk2.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    //바인딩 객체선언
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩 초기화
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPurchase.setOnClickListener {
            Toast.makeText(requireContext(),
                getString(R.string.string_msg_move_to_purchase), Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, PurchaseFragment())
                .commit()
            (activity as MainActivity).updateBottomMenu(R.id.purchaseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}