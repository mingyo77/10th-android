package com.example.umcweek2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umcweek2.databinding.FragmentShopPlaceholderBinding

class ShopPlaceholderFragment : Fragment() {
    private var _binding: FragmentShopPlaceholderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopPlaceholderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString(ARG_TITLE).orEmpty()
        binding.tvPlaceholder.text = "$title 탭은 5주차 과제를 위해 비워둔 화면입니다."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "arg_title"

        fun newInstance(title: String): ShopPlaceholderFragment {
            return ShopPlaceholderFragment().apply {
                arguments = Bundle().apply { putString(ARG_TITLE, title) }
            }
        }
    }
}
