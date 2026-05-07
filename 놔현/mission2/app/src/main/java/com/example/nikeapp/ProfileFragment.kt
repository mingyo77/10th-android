package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nikeapp.databinding.FragmentProfileBinding
import com.example.nikeapp.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel에 데이터 요청
        viewModel.loadUser()
        viewModel.loadUserList()

        // 유저 정보 관찰
        lifecycleScope.launch {
            viewModel.user.collect { user ->
                user?.let {
                    binding.profileNameTv.text = "${it.firstName} ${it.lastName}"
                    binding.profileEmailTv.text = it.email
                    Glide.with(requireContext())
                        .load(it.avatar)
                        .circleCrop()
                        .into(binding.profileIconIv)
                }
            }
        }

        // 유저 리스트 관찰
        lifecycleScope.launch {
            viewModel.userList.collect { userList ->
                val adapter = FollowingAdapter(userList.toMutableList())
                binding.profileFollowingRv.adapter = adapter
                binding.profileFollowingRv.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}