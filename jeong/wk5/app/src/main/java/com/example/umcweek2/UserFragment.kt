package com.example.umcweek2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.umcweek2.databinding.FragmentUserBinding
import com.example.umcweek2.presentation.user.UserViewModel
import com.example.umcweek2.user.FollowingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingAdapter: FollowingAdapter
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeUserPage()
        viewModel.loadUserPage()

        ViewCompat.setOnApplyWindowInsetsListener(binding.userRoot) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(
                v.paddingLeft,
                topInset,
                v.paddingRight,
                v.paddingBottom
            )
            insets
        }
    }

    private fun initRecyclerView() {
        followingAdapter = FollowingAdapter(emptyList())
        binding.rvFollowing.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFollowing.adapter = followingAdapter
    }

    private fun observeUserPage() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.tvLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    binding.tvError.visibility = if (state.errorMessage.isNullOrBlank()) View.GONE else View.VISIBLE
                    binding.tvError.text = state.errorMessage.orEmpty()

                    state.profile?.let { profile ->
                        binding.tvNickname.text = "${profile.firstName} ${profile.lastName}"
                        binding.ivProfile.load(profile.avatar)
                        val benefitCount = (profile.id - 1).coerceAtLeast(0)
                        binding.tvBenefitCount.text = "${benefitCount}개 사용 가능"
                    }

                    followingAdapter.submitList(state.following)
                    binding.tvFollowingTitle.text = "팔로잉 (${state.following.size})"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}