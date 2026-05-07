package com.example.umc.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc.databinding.FragmentProfileBinding
import com.example.umc.ui.adapter.FollowerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val followerAdapter = FollowerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvFollowers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowers.adapter = followerAdapter
        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressProfile.isVisible = state.isLoading
                    binding.tvProfileError.isVisible = state.errorMessage != null
                    binding.tvProfileError.text = state.errorMessage

                    state.myProfile?.let { user ->
                        binding.tvMyNickname.text = user.nickname
                        binding.tvMyEmail.text = user.email
                        binding.tvMyUserId.text = "userId: ${user.id}"
                        Glide.with(binding.ivMyProfile)
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivMyProfile)
                    }

                    followerAdapter.submitList(state.followers)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
