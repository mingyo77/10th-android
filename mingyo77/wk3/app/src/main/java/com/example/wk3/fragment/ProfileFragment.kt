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
import com.bumptech.glide.Glide
import com.example.wk3.ViewModel.ProfileViewModel
import com.example.wk3.adapter.FollowingAdapter
import com.example.wk3.data.RetrofitClient
import com.example.wk3.data.UserResponse
import com.example.wk3.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel : ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private lateinit var followingAdapter: FollowingAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.fetchUserInfo()
        viewModel.fetchFollowingList()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.userData.collect { userResponse ->
                        userResponse?.let {
                            binding.textView8.text = "${it.data.firstName} ${it.data.lastName}"
                            Glide.with(this@ProfileFragment)
                                .load(it.data.avatar)
                                .circleCrop()
                                .into(binding.imageView6)
                        }
                    }
                }

                launch {
                    viewModel.followingList.collect { users ->
                        followingAdapter.updateData(users)
                    }
                }
            }
        }
    }
    private fun setupRecyclerView(){
        //빈 리스트로 시작, 어댑터로부터 받아오기
        followingAdapter = FollowingAdapter(emptyList())
        binding.recyclerFollowing.apply{
            adapter = followingAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}