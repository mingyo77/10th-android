package com.example.umcweek1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.umcweek1.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emotionTextViews = listOf(
            binding.tvHappy,
            binding.tvExcited,
            binding.tvNormal,
            binding.tvAnxious,
            binding.tvAngry
        )
        var selectedEmotionTextView: TextView? = null

        fun resetTextColor() {
            val defaultColor = ContextCompat.getColor(this, R.color.emotion_text_default)
            emotionTextViews.forEach { it.setTextColor(defaultColor) }
        }

        fun setEmotionSelection(targetTextView: TextView, selectedColorResId: Int) {
            if (selectedEmotionTextView == targetTextView) {
                resetTextColor()
                selectedEmotionTextView = null
            } else {
                resetTextColor()
                targetTextView.setTextColor(ContextCompat.getColor(this, selectedColorResId))
                selectedEmotionTextView = targetTextView
            }
        }

        binding.layoutHappy.setOnClickListener {
            setEmotionSelection(binding.tvHappy, R.color.emotion_happy_selected)
        }
        binding.layoutExcited.setOnClickListener {
            setEmotionSelection(binding.tvExcited, R.color.emotion_excited_selected)
        }
        binding.layoutNormal.setOnClickListener {
            setEmotionSelection(binding.tvNormal, R.color.emotion_normal_selected)
        }
        binding.layoutAnxious.setOnClickListener {
            setEmotionSelection(binding.tvAnxious, R.color.emotion_anxious_selected)
        }
        binding.layoutAngry.setOnClickListener {
            setEmotionSelection(binding.tvAngry, R.color.emotion_angry_selected)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}