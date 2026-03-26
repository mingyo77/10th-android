package com.example.umc_task

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.umc_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //바인딩 객체 선언, lateinit = 나중에 초기화 하겠다는 의미
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val emotionTexts = listOf(
            binding.tvHappy,
            binding.tvExcited,
            binding.tvCommon,
            binding.tvNervous,
            binding.tvMad
        )
        binding.ivHappy.setOnClickListener {
            for (text in emotionTexts) {
                text.setTextColor(getColor(R.color.black))
            }
            binding.tvHappy.setTextColor(getColor(R.color.yellow))
        }

        binding.ivExcited.setOnClickListener {
            for (text in emotionTexts) {
                text.setTextColor(getColor(R.color.black))
            }
            binding.tvExcited.setTextColor(getColor(R.color.sky))
        }
        binding.ivCommon.setOnClickListener {
            for (text in emotionTexts) {
                text.setTextColor(getColor(R.color.black))
            }
            binding.tvCommon.setTextColor(getColor(R.color.blue))

        }
        binding.ivNervous.setOnClickListener {
            for (text in emotionTexts) {
                text.setTextColor(getColor(R.color.black))
            }
            binding.tvNervous.setTextColor(getColor(R.color.green))
        }
        binding.ivMad.setOnClickListener {
            for (text in emotionTexts) {
                text.setTextColor(getColor(R.color.black))
            }
            binding.tvMad.setTextColor(getColor(R.color.red))
        }
    }
}