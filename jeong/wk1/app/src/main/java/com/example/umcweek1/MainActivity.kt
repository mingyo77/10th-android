package com.example.umcweek1

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val layoutHappy = findViewById<LinearLayout>(R.id.layoutHappy)
        val layoutExcited = findViewById<LinearLayout>(R.id.layoutExcited)
        val layoutNormal = findViewById<LinearLayout>(R.id.layoutNormal)
        val layoutAnxious = findViewById<LinearLayout>(R.id.layoutAnxious)
        val layoutAngry = findViewById<LinearLayout>(R.id.layoutAngry)

        val tvHappy = findViewById<TextView>(R.id.tvHappy)
        val tvExcited = findViewById<TextView>(R.id.tvExcited)
        val tvNormal = findViewById<TextView>(R.id.tvNormal)
        val tvAnxious = findViewById<TextView>(R.id.tvAnxious)
        val tvAngry = findViewById<TextView>(R.id.tvAngry)

        fun resetTextColor() {
            val defaultColor = Color.parseColor("#000000")
            tvHappy.setTextColor(defaultColor)
            tvExcited.setTextColor(defaultColor)
            tvNormal.setTextColor(defaultColor)
            tvAnxious.setTextColor(defaultColor)
            tvAngry.setTextColor(defaultColor)
        }

        layoutHappy.setOnClickListener {
            resetTextColor()
            tvHappy.setTextColor(Color.parseColor("#FFD54F"))
        }

        layoutExcited.setOnClickListener {
            resetTextColor()
            tvExcited.setTextColor(Color.parseColor("#64B5F6"))
        }

        layoutNormal.setOnClickListener {
            resetTextColor()
            tvNormal.setTextColor(Color.parseColor("#9575CD"))
        }

        layoutAnxious.setOnClickListener {
            resetTextColor()
            tvAnxious.setTextColor(Color.parseColor("#81C784"))
        }

        layoutAngry.setOnClickListener {
            resetTextColor()
            tvAngry.setTextColor(Color.parseColor("#E57373"))
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}