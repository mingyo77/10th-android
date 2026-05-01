package com.example.nikeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nikeapp.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val apiKey = "reqres_df73b9dd436f45ee8ae8925f9dd0f2aa"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1번 유저 정보 가져오기
        lifecycleScope.launch {
            try {
                val response = ApiClient.userService.getUser(apiKey)
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    user?.let {
                        binding.profileNameTv.text = "${it.firstName} ${it.lastName}"
                        binding.profileEmailTv.text = it.email
                        Glide.with(requireContext())
                            .load(it.avatar)
                            .circleCrop()
                            .into(binding.profileIconIv)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 유저 리스트 가져오기
        lifecycleScope.launch {
            try {
                val response = ApiClient.userService.getUserList(apiKey)
                if (response.isSuccessful) {
                    val userList = response.body()?.data?.toMutableList() ?: mutableListOf()
                    val adapter = FollowingAdapter(userList)
                    binding.profileFollowingRv.adapter = adapter
                    binding.profileFollowingRv.layoutManager = LinearLayoutManager(requireContext())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}