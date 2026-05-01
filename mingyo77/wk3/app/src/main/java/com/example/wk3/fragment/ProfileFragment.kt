package com.example.wk3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wk3.data.RetrofitClient
import com.example.wk3.data.UserResponse
import com.example.wk3.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
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

        loadUserInfo()
    }

    private fun loadUserInfo() {

        RetrofitClient.userService.getUserById(1).enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data

                    user?.let {

                        val fullName = "${it.firstName} ${it.lastName}"

                        binding.textView8.text = fullName

                        Glide.with(this@ProfileFragment)
                            .load(it.avatar)
                            .circleCrop()
                            .into(binding.imageView6)
                    }
                }
            }


            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Retrofit_Error", "연결 실패: ${t.message}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}