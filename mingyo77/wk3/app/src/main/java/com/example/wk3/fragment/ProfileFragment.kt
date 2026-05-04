package com.example.wk3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.wk3.adapter.FollowingAdapter
import com.example.wk3.data.RetrofitClient
import com.example.wk3.data.UserResponse
import com.example.wk3.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {


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
        loadUserInfo()
        loadFollowingList()
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
    private fun loadUserInfo() {
        //viewLifecyclerOwner.lifecycleScope.launch:
        //화면 프래그먼트 뷰 생명주기에 맞춰 코루틴 시작, 화면이 파괴되면
        //통신도 자동으로 취소
        viewLifecycleOwner.lifecycleScope.launch {
            try{
                val response = RetrofitClient.userService.getUserById(1)
                val user = response.data

                user?.let{
                    val fullName = "${it.firstName} ${it.lastName}"
                    binding.textView8.text = fullName

                    Glide.with(this@ProfileFragment)
                        .load(it.avatar)
                        .circleCrop()
                        .into(binding.imageView6)
                }
            } catch (e: Exception){
                Log.e("Retrofit_Error", "연결 실패: ${e.message}")
            }
        }
    }
    private fun loadFollowingList(){
        viewLifecycleOwner.lifecycleScope.launch{
            try {
                //서버에서 유저리스트 가져오기
                val response = RetrofitClient.userService.getUsers(1)
                val users = response.data
                Log.d("ProfileTest", "서버에서 받아온 팔로잉 수: ${users.size}")
                //어댑터에 데이터 전달하여 화면 갱신
                followingAdapter.updateData(users)
            }catch(e: Exception){
                Log.e("Retrofit_Error", "팔로잉 목록 로드 실패: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}